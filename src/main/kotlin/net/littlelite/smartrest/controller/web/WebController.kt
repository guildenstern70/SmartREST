/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.net.littlelite.smartrest.controller.web

import net.littlelite.smartrest.SmartRestApplication
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class WebController
{
    private val logger = LoggerFactory.getLogger(WebController::class.java)

    /**
     * the REST request for / resource.
     *
     * @param model the HTTP request attributes. it will updated
     * with application's version.
     * @return the home page
     */
    @RequestMapping("/index")
    fun index(model: Model): String
    {
        logger.info("/index page")
        model.addAttribute("version", VERSION)
        return "index"
    }

    /**
     * the REST request for / resource.
     *
     * @return redirect to the index page
     */
    @RequestMapping("/")
    fun home(): String
    {
        return "redirect:index"
    }

    companion object
    {
        private const val VERSION = SmartRestApplication.VERSION
    }
}
