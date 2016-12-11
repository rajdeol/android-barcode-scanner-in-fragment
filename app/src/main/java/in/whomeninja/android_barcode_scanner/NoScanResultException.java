package in.whomeninja.android_barcode_scanner;

/**
 * Exception when scan returns no result
 * Created by RajinderPal on 12/11/2016.
 */

public class NoScanResultException extends Exception {
    public NoScanResultException() {}
    public NoScanResultException(String msg) { super(msg); }
    public NoScanResultException(Throwable cause) { super(cause); }
    public NoScanResultException(String msg, Throwable cause) { super(msg, cause); }
}
