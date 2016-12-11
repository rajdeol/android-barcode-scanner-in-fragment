package in.whomeninja.android_barcode_scanner;

/**
 * Created by Rajinder on 12/10/2016.
 */
public interface ScanResultReceiver {
    /**
     * function to receive scanresult
     * @param codeFormat format of the barcode scanned
     * @param codeContent data of the barcode scanned
     */
    public void scanResultData(String codeFormat, String codeContent);

    public void scanResultData(NoScanResultException noScanData);
}
