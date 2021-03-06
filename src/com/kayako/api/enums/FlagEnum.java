package com.kayako.api.enums;

/*
Copyright (c) 2013 Kayako

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

/**
 * The Flag Enumeration.
 */
public enum FlagEnum {
    NONE, PURPLE, ORANGE, GREEN, YELLOW, RED, BLUE;

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        switch (this) {
            case NONE:
                return "0";
            case PURPLE:
                return "1";
            case ORANGE:
                return "2";
            case GREEN:
                return "3";
            case YELLOW:
                return "4";
            case RED:
                return "5";
            case BLUE:
                return "6";
            default:
                return "";
        }

    }

    /**
     * Gets enum.
     *
     * @param access the access
     * @return the enum
     */
    public static FlagEnum getEnum(String access) {
        if (access.equalsIgnoreCase("0")) {
            return NONE;
        } else if (access.equalsIgnoreCase("1")) {
            return PURPLE;
        } else if (access.equalsIgnoreCase("2")) {
            return ORANGE;
        } else if (access.equalsIgnoreCase("3")) {
            return GREEN;
        } else if (access.equalsIgnoreCase("4")) {
            return YELLOW;
        } else if (access.equalsIgnoreCase("5")) {
            return RED;
        } else if (access.equalsIgnoreCase("6")) {
            return BLUE;
        }
        return null;
    }
}
