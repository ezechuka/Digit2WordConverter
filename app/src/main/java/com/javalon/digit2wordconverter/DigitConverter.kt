package com.javalon.digit2wordconverter

import com.javalon.digit2wordconverter.Constants.Companion.HUNDRED
import com.javalon.digit2wordconverter.Constants.Companion.HUNDREDS
import com.javalon.digit2wordconverter.Constants.Companion.MILLION
import com.javalon.digit2wordconverter.Constants.Companion.ONE_HUNDRED_THOUSAND
import com.javalon.digit2wordconverter.Constants.Companion.ONE_MILLION
import com.javalon.digit2wordconverter.Constants.Companion.ONE_THOUSAND
import com.javalon.digit2wordconverter.Constants.Companion.TENS
import com.javalon.digit2wordconverter.Constants.Companion.TEN_MILLION
import com.javalon.digit2wordconverter.Constants.Companion.TEN_THOUSAND
import com.javalon.digit2wordconverter.Constants.Companion.THOUSAND
import com.javalon.digit2wordconverter.Constants.Companion.TWENTYS
import com.javalon.digit2wordconverter.Constants.Companion.middlesConstant
import com.javalon.digit2wordconverter.Constants.Companion.onesConstant
import com.javalon.digit2wordconverter.Constants.Companion.tensConstant

class DigitConverter {

    companion object {

        private fun onesSize(figure: Int) : String {
            val toWord = StringBuilder()
            if (figure < TENS)
                toWord.append(onesConstant[figure - 1])

            return toWord.toString()
        }

        private fun tensSize(figure : Int) : String {
            var position: Int
            val toWord = StringBuilder()
            return if ((figure >= TENS) && (figure < HUNDREDS)) {
                val stringValue = figure.toString()
                if ((figure > TENS) && (figure < TWENTYS)) {
                    position = stringValue[1].toString().toInt() - 1
                    toWord.append(middlesConstant[position])
                } else if (stringValue.endsWith("0")) {
                    position = stringValue[0].toString().toInt() - 1
                    toWord.append(tensConstant[position])
                } else {
                    for (i in stringValue.indices) {
                        if (i == (stringValue.length - 1)) {
                            position = stringValue[i].toString().toInt() - 1
                            toWord.append(onesConstant[position])
                        } else {
                            position = stringValue[i].toString().toInt() - 1
                            toWord.append(tensConstant[position]).append(" ")
                        }
                    }
                }

                toWord.toString()
            } else
                onesSize(figure)
        }

        private fun hundredsSize(figure : Int) : String {
            val position: Int
            val toWord = StringBuilder()
            return if ((figure >= HUNDREDS) && (figure < ONE_THOUSAND)) {
                val stringValue = figure.toString()
                if (stringValue.endsWith("00")) {
                    position = stringValue[0].toString().toInt() - 1
                    toWord.append("${onesConstant[position]} $HUNDRED")
                } else {
                    toWord.append("${onesConstant[stringValue[0].toString().toInt() - 1]} $HUNDRED and ")

                    toWord.append(tensSize(stringValue.substring(1).toInt()))
                }

                toWord.toString()
            } else
                tensSize(figure)
        }

        private fun thousandsSize(figure : Int) : String {
            val position: Int
            val toWord = StringBuilder()
            return if ((figure >= ONE_THOUSAND) && (figure < TEN_THOUSAND)) {
                val stringValue = figure.toString()
                if (stringValue.endsWith("000")) {
                    position = stringValue[0].toString().toInt() - 1
                    toWord.append("${onesConstant[position]} $THOUSAND")
                } else {
                    toWord.append("${onesConstant[stringValue[0].toString().toInt() - 1]} $THOUSAND ")

                    toWord.append(hundredsSize(stringValue.substring(1).toInt()))
                }

                toWord.toString()
            } else
                return hundredsSize(figure)
        }

        private fun tenThousandsSize(figure : Int) : String {
            val position: Int
            val toWord = StringBuilder()
            return if ((figure >= TEN_THOUSAND) && (figure < ONE_HUNDRED_THOUSAND)) {
                val stringValue = figure.toString()
                if (stringValue.endsWith("000")) {
                    position = stringValue.substring(0, 2).toInt()
                    toWord.append("${tensSize(position)} $THOUSAND")
                } else {
                    toWord.append("${tensSize(stringValue.substring(0, 2).toInt())} $THOUSAND ")

                    toWord.append(hundredsSize(stringValue.substring(2).toInt()))
                }

                toWord.toString()
            } else
                 thousandsSize(figure)
        }

        private fun hundredThousandsSize(figure : Int) : String {
            val position: Int
            val toWord = StringBuilder()
            return if ((figure >= ONE_HUNDRED_THOUSAND) && (figure < ONE_MILLION)) {
                val stringValue = figure.toString()
                if (stringValue.endsWith("000")) {
                    position = stringValue.substring(0, 3).toInt()
                    toWord.append("${hundredsSize(position)} $THOUSAND")
                } else {
                    toWord.append("${hundredsSize(stringValue.substring(0, 3).toInt())} $THOUSAND ")

                    toWord.append(hundredsSize(stringValue.substring(3).toInt()))
                }

                toWord.toString()
            } else
                tenThousandsSize(figure)
        }

        private fun oneMillionsSize(figure : Int) : String {
            val position: Int
            val toWord = StringBuilder()
            return if ((figure >= ONE_MILLION) && (figure < TEN_MILLION)) {
                val stringValue = figure.toString()
                if (stringValue.endsWith("000")) {
                    position = stringValue[0].toString().toInt() - 1
                    toWord.append("${onesConstant[position]} $MILLION")
                } else {
                    toWord.append("${onesConstant[(stringValue[0].toString()).toInt() - 1]} $MILLION ")

                    toWord.append(hundredThousandsSize(stringValue.substring(1).toInt()))
                }

                toWord.toString()
            } else
                hundredThousandsSize(figure)
        }

        fun asWords(figure : Int) : String {
            if ((figure <= 0) || (figure >= TEN_MILLION)) throw IllegalArgumentException("figure not supported.")
            else return oneMillionsSize(figure)
        }
    }
}