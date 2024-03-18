package ai.chat2db.server.web.api.controller.rdb.request;

import java.io.Serial;

import ai.chat2db.server.web.api.controller.data.source.request.DataSourceBaseRequestInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcedureDetailRequest implements DataSourceBaseRequestInfo {

    @Serial
    private static final long serialVersionUID = -364547173428396332L;
    /**
     * Data source id
     */
    @NotNull
    private Long dataSourceId;
    /**
     * DB name
     */
    private String databaseName;

    /**
     * The space where the table is located is required by pg and oracle, but not by mysql.
     */
    private String schemaName;

    /**
     * procedure name
     */
    private String procedureName;

    /**
     * if true, refresh the cache
     */
    private boolean refresh;
}
