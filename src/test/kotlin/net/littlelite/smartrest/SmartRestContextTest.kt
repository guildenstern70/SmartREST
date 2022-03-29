/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class SmartRestContextTest
{
    @Test
    fun `Spring Context should load`()
    {
        Assertions.assertThat(true).isTrue
    }

}
