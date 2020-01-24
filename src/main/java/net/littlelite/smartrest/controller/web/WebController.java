/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.controller.web;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page Capital Reporting controller
 */
@Controller
public class WebController
{
    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    private static final String VERSION = "0.1.0";

    /**
     * the REST request for / resource.
     *
     * @param model the HTTP request attributes. it will updated
     *              with application's version.
     * @return the home page
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/index")
    public String index(@NotNull Model model)
    {
        logger.info("/index page");

        model.addAttribute("version", WebController.VERSION);
        return "index";
    }

    /**
     * the REST request for / resource.
     *
     * @return redirect to the index page
     **/
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/")
    public String home()
    {
        return "redirect:index";
    }


}
