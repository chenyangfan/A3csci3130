package com.acme.a3csci3130;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
/**
 * * This annotation should be used along with {@link android.support.test.rule}
 * or with any rule that inherits from it. When the annotation is present, the test method is
 * executed on the application's UI thread (or main thread).
 */
public class contactUiTest {
    /**
     * defines  the Rule for running the espresso Test.
     * @see ActivityTestRule for more information about rules.
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    /**
     *
     * @throws Exception when the test quits unexpectedly.
     * <ul>Four Test
     * <li>test on CreateContact</li>
     * <li>test on Click and Read</li>
     * <li>test on update Contact</li>
     * <li>test on delete Contact</li>
     * </ul>
     * @code testCreateContact()throws Exception(){...},TestOnClickContact() throws Exception{...}
     * TestOnUpdate() throws Exception{...}, TestOnDelete()throws Exception for more information.
     */
    @Test
    public void testCreateContact()throws Exception{
        onView(withId(R.id.submitButton)).perform(click());
        String name = "Zed";
        String email = "dal@dal.ca";
        String number = "123456789";
        String business = "Distributor";
        String address = "dasfa";
        String province = "NS";
        onView(withId(R.id.name)).perform(typeText(name));
        onView(withId(R.id.email)).perform(typeText(email));
        onView(withId(R.id.number)).perform(typeText(number));
        onView(withId(R.id.primaryBusiness)).perform(typeText(business));
        onView(withId(R.id.address)).perform(typeText(address));
        onView(withId(R.id.province)).perform(typeText(province));
        onView(withId(R.id.submitButton)).perform(click());

    }

    @Test
    public void TestOnClickContact() throws Exception{
            Thread.sleep(5000);
            onData(org.hamcrest.Matchers.anything()).inAdapterView(withId(R.id.listView))
                    .atPosition(1).perform(click());
        }


        @Test
    public void TestOnUpdate() throws Exception{
            Thread.sleep(5000);
            onData(org.hamcrest.Matchers.anything()).inAdapterView(withId(R.id.listView))
                    .atPosition(7).perform(click());
            onView(withId(R.id.number)).perform(clearText());
            Thread.sleep(1000);
            onView(withId(R.id.number)).perform(typeText("111111111"));
            Thread.sleep(1000);
            onView(withId(R.id.updateButton)).perform(click());
            Thread.sleep(10000);
            onData(org.hamcrest.Matchers.anything()).inAdapterView(withId(R.id.listView))
                    .atPosition(7).perform(click());
            onView(withId(R.id.number)).check(matches(withText("111111111")));
        }

    @Test
    public void TestOnDelete()throws Exception{
        Thread.sleep(2000);
        onData(org.hamcrest.Matchers.anything()).inAdapterView(withId(R.id.listView))
                .atPosition(5).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }
    }


