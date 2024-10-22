package com.creditas.loan.domain.applications.handlers.helpers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class IncomeTierCheckerTest {

    @Nested
    @DisplayName("given an income")
    inner class CheckIncomeTier {

        @Nested
        @DisplayName("when checking tier one")
        inner class TierOne {

            @Test
            fun `returns true if income is less than or equal to 3000`() {
                val result = IncomeTierChecker.isTierOne(1000.0)

                assertThat(result).isTrue()
            }

            @Test
            fun `returns false if income is greater than 3000`() {
                val result = IncomeTierChecker.isTierOne(3500.0)

                assertThat(result).isFalse()
            }
        }

        @Nested
        @DisplayName("when checking tier two")
        inner class TierTwo {

            @Test
            fun `returns true if income is greater than 3000 and less than 5000`() {
                val result = IncomeTierChecker.isTierTwo(4000.0)

                assertThat(result).isTrue()
            }

            @Test
            fun `returns false if income is less than than 3000`() {
                val result = IncomeTierChecker.isTierTwo(1000.0)

                assertThat(result).isFalse()
            }

            @Test
            fun `returns false if income is greater than 5000`() {
                val result = IncomeTierChecker.isTierTwo(9000.0)

                assertThat(result).isFalse()
            }
        }

        @Nested
        @DisplayName("when checking tier three")
        inner class TierThree {

            @Test
            fun `returns true if income is greater than or equal to 5000`() {
                val result = IncomeTierChecker.isTierThree(7000.0)

                assertThat(result).isTrue()
            }

            @Test
            fun `returns false if income is less than 5000`() {
                val result = IncomeTierChecker.isTierThree(1000.0)

                assertThat(result).isFalse()
            }
        }
    }
}
