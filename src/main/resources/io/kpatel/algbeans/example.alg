package com.example;

import java.time.LocalDate;
import java.time.Locale;

LinkedList<T> = UNode(T item, LinkedList<T> next) | EmptyUNode();
BinaryTree<T> = BNode(T item, BinaryTree<T> left, BinaryTree<T> right) | EmptyBNode();
Optional<T> = Exists(T item) | NoExists();
Either<U, V> = Left(U item) | Right(V item);

ContactInfomation = Mail(String address, String region, String city, String country, String postalCode) 
	| Email(String addr) | Phone(String number) | Fax(String number);
Person = Person(String name, LocalDate birthday, Locale prefLocale, Contact contactMethod);
