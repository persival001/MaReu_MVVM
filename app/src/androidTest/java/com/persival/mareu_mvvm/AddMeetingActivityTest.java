package com.persival.mareu_mvvm;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.persival.mareu_mvvm.ui.add.AddMeetingActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingActivityTest {

    @Rule
   public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void datePickerTest() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.datePickerButton)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(10, 2, 2023));

        onView(withText("OK")).perform(click());

        closeSoftKeyboard();
    }

    @Test
    public void timePickerTest() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.startTimeButton)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 0));

        onView(withText("OK")).perform(click());

        closeSoftKeyboard();
    }

    @Test
    public void roomChoiceTest() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.roomChoiceAddMeeting)).perform(click());

        onData(anything())
                .inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView")))
                .atPosition(4).perform(click());

        onView(withId(R.id.nameOfMeeting)).perform(scrollTo(), replaceText("Reunion"));
    }

    @Test
    public void participantsInputTest() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.participants_input)).perform(scrollTo(), replaceText("admin@lamzone.fr"), closeSoftKeyboard());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton2.perform(scrollTo(), click());

        onView(withId(R.id.participants_input)).perform(pressImeActionButton());
        onView(withId(R.id.participants_input)).perform(scrollTo(), click());
        onView(withId(R.id.participants_input)).perform(scrollTo(), replaceText("direction@lamzone.fr"), closeSoftKeyboard());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton3.perform(scrollTo(), click());

        onView(withId(R.id.participants_input)).perform(pressImeActionButton());

        onView(withId(R.id.saveButton)).perform(scrollTo(), click());
    }

    @Test
    public void isReunionAddedTest() {
        onView(withId(R.id.floatingActionButton)).perform(click());

        onView(withId(R.id.datePickerButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(10, 2, 2023));
        onView(withText("OK")).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.startTimeButton)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 0));
        onView(withText("OK")).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.roomChoiceAddMeeting)).perform(click());
        onData(anything())
                .inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView")))
                .atPosition(4).perform(click());

        onView(withId(R.id.nameOfMeeting)).perform(scrollTo(), replaceText("Reunion"));

        onView(withId(R.id.participants_input)).perform(scrollTo(), replaceText("admin@lamzone.fr"), closeSoftKeyboard());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton2.perform(scrollTo(), click());

        onView(withId(R.id.participants_input)).perform(pressImeActionButton());
        onView(withId(R.id.participants_input)).perform(scrollTo(), click());
        onView(withId(R.id.participants_input)).perform(scrollTo(), replaceText("direction@lamzone.fr"), closeSoftKeyboard());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton3.perform(scrollTo(), click());

        onView(withId(R.id.participants_input)).perform(pressImeActionButton());

        onView(withId(R.id.saveButton)).perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_name), withText("Reunion Le 10 FEV 2023 à 14:00"),
                        withParent(allOf(withId(R.id.item),
                                withParent(withId(R.id.listOfMeeting)))),
                        isDisplayed()));
        textView.check(matches(withText("Reunion Le 10 FEV 2023 à 14:00")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
