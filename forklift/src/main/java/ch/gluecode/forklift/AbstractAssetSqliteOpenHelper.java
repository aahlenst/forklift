package ch.gluecode.forklift;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * A helper class that allows access to SQLite databases shipped as asset.
 *
 * <p>To access a SQLite database that is shipped as asset, implement this class. Overriding the lifecycle callbacks is
 * optional.</p>
 */
public abstract class AbstractAssetSqliteOpenHelper extends AbstractBundledSqliteOpenHelper {

    private static final String TAG = AbstractAssetSqliteOpenHelper.class.getSimpleName();

    private final String assetName;

    /**
     * Creates a new helper instance that allows to access a bundled SQLite database stored in the application's assets
     * directory. The database is not actually
     * opened until {@link ch.gluecode.forklift.AbstractBundledSqliteOpenHelper#getDatabase()} is called.
     *
     * @param context Context to use to access the database.
     * @param assetName Name of the bundled database file in the application's asset directory.
     * @param databaseName Name of the database in the application directory to access.
     * @param version Version number of the database (>= 1).
     */
    protected AbstractAssetSqliteOpenHelper(Context context, String assetName, String databaseName, int version) {
        super(context, databaseName, version);
        this.assetName = assetName;
    }

    /**
     * Creates a new helper instance that allows to access a bundled SQLite database stored in the application's assets
     * directory. The database is not actually
     * opened until {@link ch.gluecode.forklift.AbstractBundledSqliteOpenHelper#getDatabase()} is called.
     *
     * @param context Context to use to access the database.
     * @param assetName Name of the bundled database file in the application's asset directory.
     * @param databaseName Name of the database in the application directory to access.
     * @param version Version number of the database (>= 1).
     * @param factory Factory to use for creating cursor objects.
     */
    public AbstractAssetSqliteOpenHelper(Context context, String assetName, String databaseName,
                                         int version, SQLiteDatabase.CursorFactory factory) {
        super(context, databaseName, version, factory);

        this.assetName = assetName;
    }

    /**
     * Gets the name of the asset that is used to populate the database.
     *
     * @return Name of the asset.
     */
    public String getAssetName() {
        return assetName;
    }

    @Override
    protected final InputStream getBundledDatabase() {
        try {
            Log.i(TAG, "Trying to open asset file " + assetName + ".");
            return getContext().getAssets().open(assetName);
        } catch (IOException e) {
            throw new BundledSqliteOpenHelperException("Failed to open asset file " + assetName + ".", e);
        }
    }
}
