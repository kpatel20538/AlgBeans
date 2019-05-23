package com.example2;

import java.time.LocalDate;
import java.util.Locale;

ContactInformation = Mail(String address, String region, String city, String country, String postalCode="34344")
	| Email(String addr) | Phone(int[] number = {1, 2, 3, 4}) | Fax(boolean isCallable = false && (Boolean) true || false && (34.0 > 60.0));
People = Person(String name, LocalDate birthday, Locale prefLocale, Contact contactMethod);