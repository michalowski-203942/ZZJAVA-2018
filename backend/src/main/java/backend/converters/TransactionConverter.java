package backend.converters;

import backend.datastore.entities.Transaction;
import backend.dto.TransactionInfo;

public class TransactionConverter {
    public static TransactionInfo toTransactionInfo(Transaction t){
        return TransactionInfo.builder()
                .id(t.getId())
                .category(t.getCategory().getName())
                .date(t.getDate())
                .description(t.getDescription())
                .value(t.getValue())
                .build();
    }
}
