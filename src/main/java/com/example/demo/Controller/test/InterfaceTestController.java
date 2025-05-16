package com.example.demo.Controller.test;

import com.example.demo.Model.intarface.test.ICall;
import com.example.demo.Model.test.Cat;
import com.example.demo.Model.test.Dog;

public class InterfaceTestController {

	public static void main(String[] args) {

		ICall call = new Cat();
		ICall call2 = new Dog();

	}

}
