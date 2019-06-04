package fr.sparkit.crm.util.errors;

/**
 * Custom API exceptions errors status codes, should be unique for accounting
 * module. Any error code defined should be 20k > ERROR_CODE < 21k.
 *
 * @author amine
 */

public class ApiErrors {

    /*
     * TODO : add custom error codes depending on exception
     *
     */
    private ApiErrors() {
        super();
    }

    public static class Crm {

        private Crm() {
            super();
        }

        public static final int ENTITY_NOT_FOUND = 20004;
        public static final int LABEL_MIN_LENGTH = 20003;
        public static final int PARENT_ACCOUNT_DONT_EXISTS = 20000;
        public static final int ACCOUNT_EXISTING_CODE = 20001;
        public static final int ACCOUNT_EXISTING_LABEL = 20002;
        public static final int DOCUMENT_ACCOUNT_AMOUNT_CODE = 20100;
        public static final int DOCUMENT_ACCOUNT_WITHOUT_LINES_CODE = 20101;
        public static final int ACCOUNT_CODE_EXISTS = 20300;
        public static final int ACCOUNT_CREDIT_DEBIT_IS_DIFFERENT = 20301;
        public static final int ACCOUNT_CODE_DIFFERENT_THAN_PARENT = 20302;
        public static final int ACCOUNT_NEGATIVE_CREDIT_OR_DEBIT = 20303;
        public static final int JOURNAL_CODE_EXISTS = 20400;
        public static final int CHART_EXISTING_CODE_AND_LABEL = 20500;
        public static final int FISCAL_YEAR_INEXISTANT_FISCAL_YEAR = 20501;
        public static final int FISCAL_YEAR_MISSING_PARAMETERS = 20502;
        public static final int FISCAL_YEAR_DATES_OVERLAP_ERROR = 20503;
        public static final int FISCAL_YEAR_DATES_ORDER_INVALID = 20504;

    }

}