package com.renrawnalon.computersim;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.renrawnalon.computersim.activity.MainActivity;
import com.renrawnalon.computersim.computer.Computer;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class KitamuraTest {

	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
			MainActivity.class);

	@Test
	public void sample() {
		//1 + 2 = 3
		onView(withId(R.id.button1)).perform(click());
		onView(withId(R.id.buttonPlus)).perform(click());
		onView(withId(R.id.button2)).perform(click());
		onView(withId(R.id.buttonEquals)).perform(click());

		onView(withId(R.id.outputTextView)).check(matches(withText("Output: 3")));
	}

	@Test
	public void sample2(){
		MainActivity activity = mActivityRule.getActivity();
		try {
			Field field = MainActivity.class.getDeclaredField("computer");
			field.setAccessible(true);
			Computer computer = (Computer) field.get(activity);
			Assert.assertNotNull(computer);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}
