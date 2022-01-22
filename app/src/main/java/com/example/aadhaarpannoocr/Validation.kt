package com.example.aadhaarpannoocr

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.validatePanCard(): Boolean {
    // Regex to check valid PAN Card number.
    val regex = "[A-Z]{5}[0-9]{4}[A-Z]"
    // Compile the ReGex
    val p: Pattern = Pattern.compile(regex)
    val m: Matcher = p.matcher(this)

    return m.matches()
}

//4719 3187 1843 with space in between
fun String.validateAadhaarCard(): Boolean {
    val regex =  "^[2-9][0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$"
    val p: Pattern = Pattern.compile(regex)
    val m: Matcher = p.matcher(this)

    return m.matches()
}

//471931871578 without space in between
fun String.validateAadhaarCardWithoutSpace(): Boolean {
    val regex =  "^[2-9][0-9]{3}[0-9]{4}[0-9]{4}$"
    val p: Pattern = Pattern.compile(regex)
    val m: Matcher = p.matcher(this)

    return m.matches()
}