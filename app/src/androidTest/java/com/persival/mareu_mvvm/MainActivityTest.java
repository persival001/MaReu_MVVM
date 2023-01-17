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
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton), withContentDescription("fab"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragmentContainerView),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.datePickerButton), withText("16 JAN 2023"), withContentDescription("Date de la réunion"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.startTimeButton), withText("Heure de la réunion"), withContentDescription("Heure de début de réunion"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.roomChoiceAddMeeting), withContentDescription("Numéro de salle de réunion"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView")))
                .atPosition(4);
        appCompatCheckedTextView.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.nameOfMeeting),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.nameOfMeetingLayout), withContentDescription("Objet de la réunion")),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), click());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.nameOfMeeting),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.nameOfMeetingLayout), withContentDescription("Objet de la réunion")),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("reu"), closeSoftKeyboard());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.participants_input),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.participants_layout), withContentDescription("E-mails des participants")),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("gtf@fhh.nbg"), closeSoftKeyboard());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton2.perform(scrollTo(), click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.participants_input),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.participants_layout), withContentDescription("E-mails des participants")),
                                        0),
                                0)));
        textInputEditText4.perform(pressImeActionButton());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.participants_input),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.participants_layout), withContentDescription("E-mails des participants")),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.participants_input),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.participants_layout), withContentDescription("E-mails des participants")),
                                        0),
                                0)));
        textInputEditText6.perform(scrollTo(), replaceText("ret@nb.mku"), closeSoftKeyboard());

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.email_ok_button), withContentDescription("Ajouter un mail"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        floatingActionButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.participants_input),
                        childAtPosition(
                                childAtPosition(
                                        allOf(withId(R.id.participants_layout), withContentDescription("E-mails des participants")),
                                        0),
                                0)));
        textInputEditText7.perform(pressImeActionButton());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.saveButton), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.item_list_name), withText("reu Le 16 JAN 2023 ? 00:00"),
                        withParent(allOf(withId(R.id.item),
                                withParent(withId(R.id.listOfMeeting)))),
                        isDisplayed()));
        textView.check(matches(withText("reu Le 16 JAN 2023 à 00:00")));
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
