package org.apache.tajo.jdbc; /**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.tajo.TajoConstants;
import org.apache.tajo.catalog.Column;
import org.apache.tajo.catalog.TableDesc;
import org.apache.tajo.client.ResultSetUtil;
import org.apache.tajo.client.TajoClient;
import org.apache.tajo.common.TajoDataTypes.Type;
import org.apache.tajo.datum.NullDatum;
import org.apache.tajo.datum.TextDatum;

import java.sql.*;
import java.util.*;

/**
 * TajoDatabaseMetaData.
 */
public class TajoDatabaseMetaData implements DatabaseMetaData {
  private static final char SEARCH_STRING_ESCAPE = '\\';

  private final TajoConnection conn;

  public TajoDatabaseMetaData(TajoConnection conn) {
    this.conn = conn;
  }

  @Override
  public boolean allProceduresAreCallable()
      throws SQLException {
    return true;
  }

  @Override
  public boolean allTablesAreSelectable()
      throws SQLException {
    return true;
  }

  @Override
  public String getURL()
      throws SQLException {
    return conn.getUri();
  }

  @Override
  public String getUserName()
      throws SQLException {
    return "tajo";
  }

  @Override
  public boolean isReadOnly()
      throws SQLException {
    return true;
  }

  @Override
  public String getDatabaseProductName()
      throws SQLException {
    return "Tajo";
  }

  @Override
  public String getDatabaseProductVersion()
      throws SQLException {
    //TODO get from tajo master
    return TajoConstants.TAJO_VERSION;
  }

  @Override
  public String getDriverName()
      throws SQLException {
    return "tajo";
  }

  @Override
  public String getDriverVersion()
      throws SQLException {
    return TajoDriver.MAJOR_VERSION + "." + TajoDriver.MINOR_VERSION;
  }

  @Override
  public int getDriverMajorVersion() {
    return TajoDriver.MAJOR_VERSION;
  }

  @Override
  public int getDriverMinorVersion() {
    return TajoDriver.MINOR_VERSION;
  }

  @Override
  public String getIdentifierQuoteString()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getIdentifierQuoteString not supported");
  }

  @Override
  public String getSQLKeywords()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getSQLKeywords not supported");
  }

  @Override
  public String getNumericFunctions()
      throws SQLException {
    return "";
  }

  @Override
  public String getStringFunctions()
      throws SQLException {
    return "";
  }

  @Override
  public String getSystemFunctions()
      throws SQLException {
    return "";
  }

  @Override
  public String getTimeDateFunctions()
      throws SQLException {
    return "";
  }

  @Override
  public String getSearchStringEscape()
      throws SQLException {
    return "\\";
  }

  @Override
  public String getExtraNameCharacters()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getExtraNameCharacters not supported");
  }

  @Override
  public String getSchemaTerm()
      throws SQLException {
    return "";
  }

  @Override
  public String getProcedureTerm()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getProcedureTerm not supported");
  }

  @Override
  public String getCatalogTerm()
      throws SQLException {
    return "database";
  }

  @Override
  public String getCatalogSeparator()
      throws SQLException {
    return ".";
  }

  @Override
  public int getMaxBinaryLiteralLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxBinaryLiteralLength not supported");
  }

  @Override
  public int getMaxCharLiteralLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxCharLiteralLength not supported");
  }

  @Override
  public int getMaxColumnNameLength()
      throws SQLException {
    return 128;
  }

  @Override
  public int getMaxColumnsInGroupBy()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxColumnsInGroupBy not supported");
  }

  @Override
  public int getMaxColumnsInIndex()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxColumnsInIndex not supported");
  }

  @Override
  public int getMaxColumnsInOrderBy()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxColumnsInOrderBy not supported");
  }

  @Override
  public int getMaxColumnsInSelect()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxColumnsInSelect not supported");
  }

  @Override
  public int getMaxColumnsInTable()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxColumnsInTable not supported");
  }

  @Override
  public int getMaxConnections()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxConnections not supported");
  }

  @Override
  public int getMaxCursorNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxCursorNameLength not supported");
  }

  @Override
  public int getMaxIndexLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxIndexLength not supported");
  }

  @Override
  public int getMaxSchemaNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxSchemaNameLength not supported");
  }

  @Override
  public int getMaxProcedureNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxProcedureNameLength not supported");
  }

  @Override
  public int getMaxCatalogNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxCatalogNameLength not supported");
  }

  @Override
  public int getMaxRowSize()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxRowSize not supported");
  }

  @Override
  public boolean doesMaxRowSizeIncludeBlobs()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("doesMaxRowSizeIncludeBlobs not supported");
  }

  @Override
  public int getMaxStatementLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxStatementLength not supported");
  }

  @Override
  public int getMaxStatements()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxStatements not supported");
  }

  @Override
  public int getMaxTableNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxTableNameLength not supported");
  }

  @Override
  public int getMaxTablesInSelect()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxTablesInSelect not supported");
  }

  @Override
  public int getMaxUserNameLength()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getMaxUserNameLength not supported");
  }

  @Override
  public int getDefaultTransactionIsolation()
      throws SQLException {
    return Connection.TRANSACTION_NONE;
  }

  @Override
  public boolean dataDefinitionCausesTransactionCommit()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("dataDefinitionCausesTransactionCommit not supported");
  }

  @Override
  public boolean dataDefinitionIgnoredInTransactions()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("dataDefinitionIgnoredInTransactions not supported");
  }

  @Override
  public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("stored procedures not supported");
  }

  @Override
  public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("stored procedures not supported");
  }

  /**
   * Convert a pattern containing JDBC catalog search wildcards into
   * Java regex patterns.
   *
   * @param pattern input which may contain '%' or '_' wildcard characters, or
   * these characters escaped using {@link #getSearchStringEscape()}.
   * @return replace %/_ with regex search characters, also handle escaped
   * characters.
   */
  private String convertPattern(final String pattern) {
    if (pattern == null) {
      return ".*";
    } else {
      StringBuilder result = new StringBuilder(pattern.length());

      boolean escaped = false;
      for (int i = 0, len = pattern.length(); i < len; i++) {
        char c = pattern.charAt(i);
        if (escaped) {
          if (c != SEARCH_STRING_ESCAPE) {
            escaped = false;
          }
          result.append(c);
        } else {
          if (c == SEARCH_STRING_ESCAPE) {
            escaped = true;
            continue;
          } else if (c == '%') {
            result.append(".*");
          } else if (c == '_') {
            result.append('.');
          } else {
            result.append(Character.toLowerCase(c));
          }
        }
      }

      return result.toString();
    }
  }

  @Override
  public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
      throws SQLException {
    try {
      final List<MetaDataTuple> resultTables = new ArrayList<MetaDataTuple>();
      final String resultCatalog;
      if (catalog == null) {
        resultCatalog = "default";
      } else {
        resultCatalog = catalog;
      }

      String regtableNamePattern = convertPattern(tableNamePattern);
      try {
        TajoClient tajoClient = conn.getTajoClient();
        List<String> tableNames = tajoClient.getTableList();
        for (String eachTableName: tableNames) {
          if (eachTableName.matches(regtableNamePattern)) {
            MetaDataTuple tuple = new MetaDataTuple(5);

            int index = 0;
            tuple.put(index++, new TextDatum(resultCatalog));  //TABLE_CAT
            tuple.put(index++, NullDatum.get());   //TABLE_SCHEM
            tuple.put(index++, new TextDatum(eachTableName));
            tuple.put(index++, new TextDatum("TABLE"));   //TABLE_TYPE
            tuple.put(index++, NullDatum.get());   //REMARKS

            resultTables.add(tuple);
          }
        }
        Collections.sort(resultTables, new Comparator<MetaDataTuple> () {
          @Override
          public int compare(MetaDataTuple table1, MetaDataTuple table2) {
            return table1.getText(2).compareTo(table2.getText(2));
          }
        });
      } catch (Exception e) {
        e.printStackTrace();
        throw new SQLException(e);
      }
      TajoMetaDataResultSet result = new TajoMetaDataResultSet(
          Arrays.asList("TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "TABLE_TYPE", "REMARKS"),
          Arrays.asList(Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR),
          resultTables);

      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new SQLException(e.getMessage(), e);
    }
  }

  @Override
  public ResultSet getSchemas()
      throws SQLException {
    return getSchemas(null, null);
  }

  @Override
  public ResultSet getCatalogs()
      throws SQLException {
    List<MetaDataTuple> columns = new ArrayList<MetaDataTuple>();
    MetaDataTuple tuple = new MetaDataTuple(1);
    tuple.put(0, new TextDatum("default"));
    columns.add(tuple);

    ResultSet result = new TajoMetaDataResultSet(
        Arrays.asList("TABLE_CAT")
        , Arrays.asList(Type.VARCHAR)
        , columns);

    return result;
  }

  @Override
  public ResultSet getTableTypes()
      throws SQLException {
    List<MetaDataTuple> columns = new ArrayList<MetaDataTuple>();
    MetaDataTuple tuple = new MetaDataTuple(1);
    tuple.put(0, new TextDatum("TABLE"));
    columns.add(tuple);

    ResultSet result = new TajoMetaDataResultSet(
        Arrays.asList("TABLE_TYPE")
        , Arrays.asList(Type.VARCHAR)
        , columns);

    return result;
  }

  @Override
  public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types)
      throws SQLException {
    List<MetaDataTuple> columns = new ArrayList<MetaDataTuple>();

    return new TajoMetaDataResultSet(
        Arrays.asList("TYPE_CAT", "TYPE_SCHEM", "TYPE_NAME", "CLASS_NAME", "DATA_TYPE"
            , "REMARKS", "BASE_TYPE")
        , Arrays.asList(Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.INT4, Type.VARCHAR, Type.INT4)
        , columns);
  }

  @Override
  public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
      throws SQLException {
    List<MetaDataTuple> columns = new ArrayList<MetaDataTuple>();
    try {
      if (catalog == null) {
        catalog = "default";
      }

      String regtableNamePattern = convertPattern(tableNamePattern);
      String regcolumnNamePattern = convertPattern(columnNamePattern);

      List<String> tables = conn.getTajoClient().getTableList();
      for (String table: tables) {
        if (table.matches(regtableNamePattern)) {
          TableDesc tableDesc = conn.getTajoClient().getTableDesc(table);
          int pos = 0;
          for (Column column: tableDesc.getSchema().getColumns()) {
            if (column.getColumnName().matches(regcolumnNamePattern)) {
              MetaDataTuple tuple = new MetaDataTuple(22);

              int index = 0;
              tuple.put(index++, new TextDatum(catalog));  //TABLE_CAT
              tuple.put(index++, NullDatum.get());  //TABLE_SCHEM
              tuple.put(index++, new TextDatum(table));  //TABLE_NAME
              tuple.put(index++, new TextDatum(column.getColumnName()));  //COLUMN_NAME
              // TODO - DATA_TYPE
              tuple.put(index++, new TextDatum("" + ResultSetUtil.tajoTypeToSqlType(column.getDataType())));
              tuple.put(index++, new TextDatum(ResultSetUtil.toSqlType(column.getDataType())));  //TYPE_NAME
              tuple.put(index++, new TextDatum("0"));  //COLUMN_SIZE
              tuple.put(index++, new TextDatum("0"));  //BUFFER_LENGTH
              tuple.put(index++, new TextDatum("0"));  //DECIMAL_DIGITS
              tuple.put(index++, new TextDatum("0"));  //NUM_PREC_RADIX
              tuple.put(index++, new TextDatum("" + DatabaseMetaData.columnNullable));  //NULLABLE
              tuple.put(index++, NullDatum.get());  //REMARKS
              tuple.put(index++, NullDatum.get());  //COLUMN_DEF
              tuple.put(index++, NullDatum.get());  //SQL_DATA_TYPE
              tuple.put(index++, NullDatum.get());  //SQL_DATETIME_SUB
              tuple.put(index++, new TextDatum("0"));  //CHAR_OCTET_LENGTH
              tuple.put(index++, new TextDatum("" + pos));  //ORDINAL_POSITION
              tuple.put(index++, new TextDatum("YES"));  //IS_NULLABLE
              tuple.put(index++, NullDatum.get());  //SCOPE_CATLOG
              tuple.put(index++, NullDatum.get());  //SCOPE_SCHEMA
              tuple.put(index++, NullDatum.get());  //SCOPE_TABLE
              tuple.put(index++, new TextDatum("0"));  //SOURCE_DATA_TYPE
              columns.add(tuple);
            }
            pos++;
          }
        }
      }

      return new TajoMetaDataResultSet(
          Arrays.asList("TABLE_CAT", "TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME", "DATA_TYPE"
              , "TYPE_NAME", "COLUMN_SIZE", "BUFFER_LENGTH", "DECIMAL_DIGITS", "NUM_PREC_RADIX"
              , "NULLABLE", "REMARKS", "COLUMN_DEF", "SQL_DATA_TYPE", "SQL_DATETIME_SUB"
              , "CHAR_OCTET_LENGTH", "ORDINAL_POSITION", "IS_NULLABLE", "SCOPE_CATLOG", "SCOPE_SCHEMA"
              , "SCOPE_TABLE", "SOURCE_DATA_TYPE")
          , Arrays.asList(Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR, Type.INT4
              , Type.VARCHAR, Type.INT4, Type.INT4, Type.INT4, Type.INT4
              , Type.INT4, Type.VARCHAR, Type.VARCHAR, Type.INT4, Type.INT4
              , Type.INT4, Type.INT4, Type.VARCHAR, Type.VARCHAR, Type.VARCHAR
              , Type.VARCHAR, Type.INT4)
          , columns);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SQLException(e);
    }
  }

  @Override
  public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("privileges not supported");
  }

  @Override
  public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("privileges not supported");
  }

  @Override
  public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("row identifiers not supported");
  }

  @Override
  public ResultSet getVersionColumns(String catalog, String schema, String table)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("version columns not supported");
  }

  @Override
  public ResultSet getPrimaryKeys(String catalog, String schema, String table)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("primary keys not supported");
  }

  @Override
  public ResultSet getImportedKeys(String catalog, String schema, String table)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("imported keys not supported");
  }

  @Override
  public ResultSet getExportedKeys(String catalog, String schema, String table)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("exported keys not supported");
  }

  @Override
  public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("cross reference not supported");
  }

  @Override
  public ResultSet getTypeInfo()
      throws SQLException {
    throw new UnsupportedOperationException("getTypeInfo not supported");
  }

  @Override
  public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("indexes not supported");
  }

  @Override
  public boolean deletesAreDetected(int type)
      throws SQLException {
    return false;
  }

  @Override
  public boolean insertsAreDetected(int type)
      throws SQLException {
    return false;
  }

  @Override
  public Connection getConnection()
      throws SQLException {
    return conn;
  }

  @Override
  public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("type hierarchies not supported");
  }

  @Override
  public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("type hierarchies not supported");
  }

  @Override
  public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("user-defined types not supported");
  }

  @Override
  public int getResultSetHoldability()
      throws SQLException {
    return ResultSet.HOLD_CURSORS_OVER_COMMIT;
  }

  @Override
  public int getDatabaseMajorVersion()
      throws SQLException {
    return TajoDriver.MAJOR_VERSION;
  }

  @Override
  public int getDatabaseMinorVersion()
      throws SQLException {
    return TajoDriver.MINOR_VERSION;
  }

  @Override
  public int getJDBCMajorVersion()
      throws SQLException {
    return TajoDriver.JDBC_VERSION_MAJOR;
  }

  @Override
  public int getJDBCMinorVersion()
      throws SQLException {
    return TajoDriver.JDBC_VERSION_MINOR;
  }

  @Override
  public int getSQLStateType()
      throws SQLException {
    return DatabaseMetaData.sqlStateSQL;
  }

  @Override
  public RowIdLifetime getRowIdLifetime()
      throws SQLException {
    return RowIdLifetime.ROWID_UNSUPPORTED;
  }

  @Override
  public ResultSet getSchemas(String catalog, String schemaPattern)
      throws SQLException {
    return new TajoMetaDataResultSet(
        Arrays.asList("TABLE_SCHEM", "TABLE_CATALOG"),
        Arrays.asList(Type.VARCHAR, Type.VARCHAR),
        null);
  }

  @Override
  public boolean autoCommitFailureClosesAllResultSets()
      throws SQLException {
    return false;
  }

  @Override
  public ResultSet getClientInfoProperties()
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getClientInfoProperties not supported");
  }

  @Override
  public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getFunctions not supported");
  }

  @Override
  public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern)
      throws SQLException {
    throw new SQLFeatureNotSupportedException("getFunctionColumns not supported");
  }

  @Override
  public boolean isCatalogAtStart() throws SQLException {
    return false;
  }

  @Override
  public boolean locatorsUpdateCopy() throws SQLException {
    return false;
  }

  @Override
  public boolean nullPlusNonNullIsNull() throws SQLException {
    return false;
  }

  @Override
  public boolean nullsAreSortedAtEnd() throws SQLException {
    return true;
  }

  @Override
  public boolean nullsAreSortedAtStart() throws SQLException {
    return false;
  }

  @Override
  public boolean nullsAreSortedHigh() throws SQLException {
    return true;
  }

  @Override
  public boolean nullsAreSortedLow() throws SQLException {
    return false;
  }

  @Override
  public boolean othersDeletesAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean othersInsertsAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean othersUpdatesAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean ownDeletesAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean ownInsertsAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean ownUpdatesAreVisible(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean storesLowerCaseIdentifiers() throws SQLException {
    return true;
  }

  @Override
  public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean storesMixedCaseIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean storesUpperCaseIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsANSI92EntryLevelSQL() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsANSI92FullSQL() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsANSI92IntermediateSQL() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsAlterTableWithAddColumn() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsAlterTableWithDropColumn() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsBatchUpdates() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCatalogsInDataManipulation() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCatalogsInProcedureCalls() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCatalogsInTableDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsColumnAliasing() throws SQLException {
    return true;
  }

  @Override
  public boolean supportsConvert() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsConvert(int fromType, int toType) throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCoreSQLGrammar() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsCorrelatedSubqueries() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsDataDefinitionAndDataManipulationTransactions()
      throws SQLException {
    return false;
  }

  @Override
  public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsDifferentTableCorrelationNames() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsExpressionsInOrderBy() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsExtendedSQLGrammar() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsFullOuterJoins() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsGetGeneratedKeys() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsGroupBy() throws SQLException {
    return true;
  }

  @Override
  public boolean supportsGroupByBeyondSelect() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsGroupByUnrelated() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsIntegrityEnhancementFacility() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsLikeEscapeClause() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsLimitedOuterJoins() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMinimumSQLGrammar() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMixedCaseIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMultipleOpenResults() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMultipleResultSets() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsMultipleTransactions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsNamedParameters() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsNonNullableColumns() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOrderByUnrelated() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsOuterJoins() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsPositionedDelete() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsPositionedUpdate() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsResultSetConcurrency(int type, int concurrency)
      throws SQLException {
    return false;
  }

  @Override
  public boolean supportsResultSetHoldability(int holdability)
      throws SQLException {
    return false;
  }

  @Override
  public boolean supportsResultSetType(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSavepoints() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSchemasInDataManipulation() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSchemasInIndexDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSchemasInProcedureCalls() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSchemasInTableDefinitions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSelectForUpdate() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsStatementPooling() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsStoredProcedures() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInComparisons() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInExists() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInIns() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsSubqueriesInQuantifieds() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsTableCorrelationNames() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsTransactionIsolationLevel(int level)
      throws SQLException {
    return false;
  }

  @Override
  public boolean supportsTransactions() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsUnion() throws SQLException {
    return false;
  }

  @Override
  public boolean supportsUnionAll() throws SQLException {
    return true;
  }

  @Override
  public boolean updatesAreDetected(int type) throws SQLException {
    return false;
  }

  @Override
  public boolean usesLocalFilePerTable() throws SQLException {
    return false;
  }

  @Override
  public boolean usesLocalFiles() throws SQLException {
    return false;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T unwrap(Class<T> iface)
      throws SQLException {
    if (isWrapperFor(iface)) {
      return (T) this;
    }
    throw new SQLFeatureNotSupportedException("No wrapper for " + iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface)
      throws SQLException {
    return iface.isInstance(this);
  }

  public boolean generatedKeyAlwaysReturned() throws SQLException {
    // JDK 1.7
    throw new SQLFeatureNotSupportedException("generatedKeyAlwaysReturned not supported");
  }

  public ResultSet getPseudoColumns(String catalog, String schemaPattern,
                                    String tableNamePattern, String columnNamePattern) throws SQLException {
    // JDK 1.7
    throw new SQLFeatureNotSupportedException("getPseudoColumns not supported");
  }
}

