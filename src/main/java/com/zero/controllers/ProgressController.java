package com.zero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.ProgressService;

@Controller
@RequestMapping("/progress")
public class ProgressController {

	@Autowired
	private ProgressService progressService;
}
