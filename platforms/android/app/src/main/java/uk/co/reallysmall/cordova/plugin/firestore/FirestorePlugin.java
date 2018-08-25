/**
 */
package uk.co.reallysmall.cordova.plugin.firestore;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Transaction;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Hashtable;
import java.util.Map;

public class FirestorePlugin extends CordovaPlugin {
    static final String TAG = "FirestorePlugin";
    private static FirebaseFirestore database;
    private Map<String, ActionHandler> handlers = new Hashtable<String, ActionHandler>();
    private Map<String, ListenerRegistration> registrations = new Hashtable<String, ListenerRegistration>();
    private Map<String, TransactionQueue> transactions = new Hashtable<String, TransactionQueue>();

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        handlers.put("collectionOnSnapshot", new CollectionOnSnapshotHandler(this));
        handlers.put("collectionUnsubscribe", new CollectionUnsubscribeHandler(this));
        handlers.put("collectionAdd", new CollectionAddHandler(this));
        handlers.put("collectionGet", new CollectionGetHandler(this));
        handlers.put("initialise", new InitialiseHandler(webView.getContext().getApplicationContext(), this));
        handlers.put("docSet", new DocSetHandler(this));
        handlers.put("docUpdate", new DocUpdateHandler(this));
        handlers.put("docOnSnapshot", new DocOnSnapshotHandler(this));
        handlers.put("docUnsubscribe", new DocUnsubscribeHandler(this));
        handlers.put("docGet", new DocGetHandler(this));
        handlers.put("docDelete", new DocDeleteHandler(this));
        handlers.put("runTransaction", new RunTransactionHandler(this));
        handlers.put("transactionDocGet", new TransactionDocGetHandler(this));
        handlers.put("transactionDocUpdate", new TransactionDocUpdateHandler(this));
        handlers.put("transactionDocSet", new TransactionDocSetHandler(this));
        handlers.put("transactionDocDelete", new TransactionDocDeleteHandler(this));
        handlers.put("transactionResolve", new TransactionResolveHandler(this));
        handlers.put("docOfSubCollectionSet", new DocOfSubCollectionSetHandler(this));
        handlers.put("docOfSubCollectionGet", new DocOfSubCollectionGetHandler(this));
        handlers.put("docOfSubCollectionUpdate", new DocOfSubCollectionUpdateHandler(this));
        handlers.put("docOfSubCollectionDelete", new DocOfSubCollectionDeleteHandler(this));
        handlers.put("subCollectionGet", new SubCollectionGetHandler(this));
        
        Log.d(TAG, "Initializing FirestorePlugin");
    }

    public FirebaseFirestore getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseFirestore database) {
        this.database = database;
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, action);

        if (handlers.containsKey(action)) {
            return handlers.get(action).handle(args, callbackContext);
        }

        return false;
    }

    public void addRegistration(String callbackId, ListenerRegistration listenerRegistration) {
        registrations.put(callbackId, listenerRegistration);
        Log.d(TAG, "Registered subscriber " + callbackId);

    }

    public void unregister(String callbackId) {
        if (registrations.containsKey(callbackId)) {
            registrations.get(callbackId).remove();
            registrations.remove(callbackId);
            Log.d(TAG, "Unregistered subscriber " + callbackId);
        }
    }

    public void storeTransaction(String transactionId, Transaction transaction) {
        TransactionQueue transactionQueue = new TransactionQueue();
        transactionQueue.transaction = transaction;
        transactions.put(transactionId, transactionQueue);
    }

    public TransactionQueue getTransaction(String transactionId) {
        return transactions.get(transactionId);
    }

    public void removeTransaction(String transactionId) {
        transactions.remove(transactionId);
    }
}
