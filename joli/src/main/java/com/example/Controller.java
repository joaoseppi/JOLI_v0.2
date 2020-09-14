/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.concurrent.atomic.AtomicLong;
import model.Pessoa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JGSS
 */

@RestController
public class Controller {
    private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("model/pessoa")
	public Pessoa greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Pessoa(counter.incrementAndGet(), String.format(template, name));
	}
}
