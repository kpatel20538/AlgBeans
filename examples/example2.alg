package com.example.union2;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.DoubleFunction;

ContactInformation = Mail(double address = ((DoubleFunction<Integer>) d -> Double.valueOf(Double.compare(10, 20) * 10.0).intValue()).apply(new int[]{4,5}[1]) * 10.0, String region, String city, String country, String postalCode= "34344" )
	| Email(String addr) | Phone(int[] number = new int[] {1, 2, 3, 4}) | Fax(boolean callable = false && (Boolean) true || false && (34.0 > 60.0  && 5 > 4 << 444 ));
People = Person(String name, LocalDate birthday, Locale prefLocale, Contact contactMethod);
