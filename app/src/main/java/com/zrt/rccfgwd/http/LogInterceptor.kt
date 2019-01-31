package com.zrt.rccfgwd.http

import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by Administrator on 2019/1/30.
 */
class LogInterceptor : HttpLoggingInterceptor.Logger {
    private var mMessage = StringBuilder()

    override fun log(message: String ) {
        try {
            if (message.startsWith("--> POST") || message.startsWith("-->GET")){
                this.mMessage.setLength(0)
            }
            if(message.startsWith("{") && message.endsWith("}") || message.startsWith("[") && message.endsWith("]")){
                message = formatJson(decodeUnicode(message))
                message = formatJson(decodeUnicode(message))
            }
            this.mMessage.append(message + "\n")
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun formatJson(jsonStr:String):String{
        if (null !=jsonStr && !"".equals(jsonStr)){
            var sb = StringBuilder()
            var last = false
            var current = ' '
            var indent = 0

            for (i in 0..jsonStr.length){
                var last = current
                current = jsonStr[i]
                when(current){
                    ',' ->{
                        sb.append(current)
                        if (last.toInt() != 92){
                            sb.append('\n')
                            addIndentBlank(sb,indent)
                        }
                    }
                    '[' -> {}
                    '{' -> {
                        sb.append(current)
                        sb.append('\n')
                        ++indent
                        addIndentBlank(sb,indent)
                    }
                    ']' ->{}
                    '}' ->{
                        sb.append('\n')
                        --indent
                        addIndentBlank(sb,indent)
                        sb.append(current)
                    }

                    else ->{
                        sb.append(current)
                    }

                }
            }
            return sb.toString()
        }else{
            return ""
        }
    }

    fun addIndentBlank(sb:StringBuilder,indent:Int){
        for (i in 0..indent){
            sb.append('\t')
        }
    }

    fun decodeUnicode(theString:String):String{
        val length = theString.length
        val outBuffer = StringBuffer(length)
        var x = 0

        while (true){
            while (true){
                while (x<length){
                    var aChar = theString[x++]
                    if (aChar.toInt() == 92){
                        aChar = theString[x++]
                        if (aChar.toInt() == 117){
                            var value = 0
                            for (i in 0..4){
                                aChar = theString[x++]
                                when(aChar){
                                    '0' ->{}
                                    '1' ->{}
                                    '2' ->{}
                                    '3' ->{}
                                    '4' ->{}
                                    '5' ->{}
                                    '6' ->{}
                                    '7' ->{}
                                    '8' ->{}
                                    '9' ->{
                                        value = (value shl 4) + aChar.toInt() - 48
                                    }
                                    ':' ->{}
                                     ';' ->{}
                                     '<' ->{}
                                     '=' ->{}
                                     '>' ->{}
                                     '?' ->{}
                                     '@' ->{}
                                     'G' ->{}
                                     'H' ->{}
                                     'I' ->{}
                                     'J' ->{}
                                     'K' ->{}
                                     'L' ->{}
                                     'M' ->{}
                                     'N' ->{}
                                     'O' ->{}
                                     'P' ->{}
                                     'Q' ->{}
                                     'R' ->{}
                                     'S' ->{}
                                     'T' ->{}
                                     'U' ->{}
                                     'V' ->{}
                                     'W' ->{}
                                     'X' ->{}
                                     'Y' ->{}
                                     'Z' ->{}
                                     '[' ->{}
                                     '\\' ->{}
                                     ']' ->{}
                                     '^' ->{}
                                     '_' ->{}
                                     '`' ->{}
                                    'A' ->{}
                                    'B' ->{}
                                    'C' ->{}
                                    'D' ->{}
                                    'E' ->{}
                                    'F' ->{
                                        value = (value shl 4)+10 + aChar.toInt() -65
                                    }
                                    'a' ->{}
                                    'b' ->{}
                                    'c' ->{}
                                    'd' ->{}
                                    'e' ->{}
                                    'f' ->{
                                        value = (value shl 4)+10 + aChar.toInt() -97
                                    }

                                    else ->{
                                        throw IllegalArgumentException("Malformed   \\uxxxx   encoding.")
                                    }
                                }
                            }
                            outBuffer.append(value)
                        }else{
                            if (aChar.toInt() == 116){
                                aChar = (9).toChar()
                            }else if (aChar.toInt() == 114){
                                aChar = (13).toChar()
                            }else if (aChar.toInt() == 110){
                                aChar = (10).toChar()
                            }else if (aChar.toInt() == 102){
                                aChar = (12).toChar()
                            }

                            outBuffer.append(aChar)
                        }
                    }else{
                        outBuffer.append(aChar)
                    }
                }

                return outBuffer.toString()
            }
        }
    }
}