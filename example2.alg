package com.example2;

import java.time.LocalDate;
import java.util.Locale;

ContactInformation = Mail(String address, String region, String city, String country, String postalCode)
	| Email(String addr) | Phone(int[] number) | Fax(boolean number);
Person = Person(String name, LocalDate birthday, Locale prefLocale, Contact contactMethod);