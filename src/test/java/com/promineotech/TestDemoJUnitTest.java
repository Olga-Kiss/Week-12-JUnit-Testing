package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;




class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
	
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		}
		else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b))
            .isInstanceOf(IllegalArgumentException.class);
		}
	}

	
	
	@Test
    void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
      	assertThat(testDemo.addPositive(4,5)).isEqualTo(9);
		assertThat(testDemo.addPositive(10,20)).isEqualTo(30);
		assertThat(testDemo.addPositive(34,52)).isEqualTo(86);
		assertThat(testDemo.addPositive(15, 5)).isEqualTo(20);
		assertThat(testDemo.addPositive(100, 10)).isEqualTo(110);
    }
	
	
	@Test
	 void assertSubtractionIsCorrect() {
		assertThat(testDemo.subtractNumbers(10, 5)).isEqualTo(5);
		assertThat(testDemo.subtractNumbers(20, 5)).isEqualTo(15);
		assertThat(testDemo.subtractNumbers(30, 5)).isEqualTo(25);
}
	

	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();	
		
		int fiveSquared = mockDemo.randomNumberSquared(); 
			assertThat(fiveSquared).isEqualTo(25);
		}
	
	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
       	   arguments(2, 4, 6, false), // exception is NOT expected
           arguments(0, 0, 0, true),  //exception is expected
          
         // test cases
           arguments(2, 3, 5, false), // a and b are positive numbers
           arguments(-1, 3, 0, true), // a is a negative number
           arguments(0, 3, 0, true), //a is zero
           arguments(2, -1, 0, true), // b is a negative number
           arguments(2, 0, 0, true)  // b is zero
       );
    }
}
