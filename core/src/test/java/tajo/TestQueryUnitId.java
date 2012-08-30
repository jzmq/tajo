package tajo;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * @author Hyunsik Choi
 */
public class TestQueryUnitId {
  @Test
  public void testQueryIds() {
    Date dateNow = new Date();
    SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMddSS");
    String timeId = dateformatYYYYMMDD.format(dateNow);
    
    QueryId queryId = new QueryId(timeId, 1);
    assertEquals("query_" + timeId + "_001", queryId.toString());
    
    SubQueryId subId = new SubQueryId(queryId, 2);
    assertEquals("query_" + timeId+"_001_002", subId.toString());
    
    ScheduleUnitId logicalQUeryUnitId =
        new ScheduleUnitId(subId, 6);
    assertEquals("query_" + timeId+"_001_002_006", 
        logicalQUeryUnitId.toString());
    
    QueryUnitId qId = new QueryUnitId(logicalQUeryUnitId, 5);
    assertEquals("query_" + timeId + "_001_002_006_000005", qId.toString());
  }

  @Test
  public void testEqualsObject() {
    Date dateNow = new Date();
    SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMddSS");
    String timeId = dateformatYYYYMMDD.format(dateNow);
    
    QueryId queryId1 = new QueryId(timeId, 1);
    QueryId queryId2 = new QueryId(timeId, 2);    
    assertNotSame(queryId1, queryId2);    
    QueryId queryId3 = new QueryId(timeId, 1);
    assertEquals(queryId1, queryId3);
    
    SubQueryId sid1 = new SubQueryId(queryId1, 1);
    SubQueryId sid2 = new SubQueryId(queryId1, 2);    
    assertNotSame(sid1, sid2);
    SubQueryId sid3 = new SubQueryId(queryId1, 1);
    assertEquals(sid1, sid3);
    
    ScheduleUnitId lqid1 = new ScheduleUnitId(sid1, 9);
    ScheduleUnitId lqid2 = new ScheduleUnitId(sid1, 10);
    assertNotSame(lqid1, lqid2);
    ScheduleUnitId lqid3 = new ScheduleUnitId(sid1, 9);
    assertEquals(lqid1, lqid3);
    
    QueryUnitId qid1 = new QueryUnitId(lqid1, 9);
    QueryUnitId qid2 = new QueryUnitId(lqid1, 10);
    assertNotSame(qid1, qid2);
    QueryUnitId qid3 = new QueryUnitId(lqid1, 9);
    assertEquals(qid1, qid3);
  }

  @Test
  public void testCompareTo() {
    Date dateNow = new Date();
    SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMddSS");
    String timeId = dateformatYYYYMMDD.format(dateNow);
    
    QueryId queryId1 = new QueryId(timeId, 1);
    QueryId queryId2 = new QueryId(timeId, 2);
    QueryId queryId3 = new QueryId(timeId, 1);
    assertEquals(-1, queryId1.compareTo(queryId2));
    assertEquals(1, queryId2.compareTo(queryId1));
    assertEquals(0, queryId3.compareTo(queryId1));
    
    SubQueryId sid1 = new SubQueryId(queryId1, 1);
    SubQueryId sid2 = new SubQueryId(queryId1, 2);    
    SubQueryId sid3 = new SubQueryId(queryId1, 1);
    assertEquals(-1, sid1.compareTo(sid2));
    assertEquals(1, sid2.compareTo(sid1));
    assertEquals(0, sid3.compareTo(sid1));
    
    ScheduleUnitId lqid1 = new ScheduleUnitId(sid1, 9);
    ScheduleUnitId lqid2 = new ScheduleUnitId(sid1, 10);
    ScheduleUnitId lqid3 = new ScheduleUnitId(sid1, 9);
    assertEquals(-1, lqid1.compareTo(lqid2));
    assertEquals(1, lqid2.compareTo(lqid1));
    assertEquals(0, lqid3.compareTo(lqid1));
    
    QueryUnitId qid1 = new QueryUnitId(lqid1, 9);
    QueryUnitId qid2 = new QueryUnitId(lqid1, 10);
    QueryUnitId qid3 = new QueryUnitId(lqid1, 9);
    assertEquals(-1, qid1.compareTo(qid2));
    assertEquals(1, qid2.compareTo(qid1));
    assertEquals(0, qid3.compareTo(qid1));
  }
  
  @Test
  public void testConstructFromString() {
    QueryIdFactory.reset();
    QueryId qid1 = QueryIdFactory.newQueryId();
    QueryId qid2 = new QueryId(qid1.toString());
    assertEquals(qid1, qid2);
    
    SubQueryId sub1 = QueryIdFactory.newSubQueryId(qid1);
    SubQueryId sub2 = new SubQueryId(sub1.toString());
    assertEquals(sub1, sub2);
    
//    QueryStepId step1 = QueryIdFactory.newQueryStepId();
//    QueryStepId step2 = new QueryStepId(step1.toString());
//    assertEquals(step1, step2);
    
    ScheduleUnitId lqid1 = QueryIdFactory.newScheduleUnitId(sub1);
    ScheduleUnitId lqid2 = new ScheduleUnitId(lqid1.toString());
    assertEquals(lqid1, lqid2);
    
    QueryUnitId u1 = QueryIdFactory.newQueryUnitId(lqid1);
    QueryUnitId u2 = new QueryUnitId(u1.toString());
    assertEquals(u1, u2);
  }
}