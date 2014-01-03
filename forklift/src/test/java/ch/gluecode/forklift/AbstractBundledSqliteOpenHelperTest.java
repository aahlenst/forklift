package ch.gluecode.forklift;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class AbstractBundledSqliteOpenHelperTest {

    private Activity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(Activity.class).create().start().resume().get();
    }

    @Test
    public void databaseSuccessfullyCopiedAndUsableOnInitialUsage() throws Exception {
        TestBundledSqliteOpenHelper helper = new TestBundledSqliteOpenHelper(activity, "cantons.db", "/cantons.db", 1);
        SQLiteDatabase db = helper.getDatabase();

        Cursor cursor = db.rawQuery("select * from cantons", null);

        assertThat(cursor)
                .hasColumns("_id", "name", "capital")
                .hasCount(26);

        cursor.close();

        Assertions.assertThat(helper.onConfigureCalled).isTrue();
        Assertions.assertThat(helper.onPostCopyCalled).isTrue();
        Assertions.assertThat(helper.onPostCopyCalledAfterOnConfigure).isTrue();
    }

    private class TestBundledSqliteOpenHelper extends AbstractBundledSqliteOpenHelper {

        private final String resourceName;
        boolean onConfigureCalled = false;
        boolean onPostCopyCalled = false;
        boolean onPostCopyCalledAfterOnConfigure;

        protected TestBundledSqliteOpenHelper(Context context, String databaseName, String resourceName, int version) {
            super(context, databaseName, version);
            this.resourceName = resourceName;
        }

        @Override
        protected InputStream getBundledDatabase() {
            return this.getClass().getResourceAsStream(resourceName);
        }

        @Override
        protected void onConfigure(SQLiteDatabase db) {
            this.onConfigureCalled = true;
        }

        @Override
        protected void onPostCopy(SQLiteDatabase db, int oldVersion, int newVersion) {
            this.onPostCopyCalled = true;
            this.onPostCopyCalledAfterOnConfigure = onConfigureCalled;
        }
    }
}
