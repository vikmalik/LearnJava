/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.learnjava.general;

import java.util.Locale;

/**
 *
 * @author vikmalik
 */
public class LearnLocalization {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        locale.getVariant();
        System.out.println(locale.toString());
    }
}
