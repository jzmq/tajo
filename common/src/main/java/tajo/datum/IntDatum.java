/**
 * 
 */
package tajo.datum;

import com.google.gson.annotations.Expose;
import tajo.datum.exception.InvalidCastException;
import tajo.datum.exception.InvalidOperationException;
import tajo.datum.json.GsonCreator;

import java.nio.ByteBuffer;

/**
 * @author Hyunsik Choi
 *
 */
public class IntDatum extends NumericDatum {
  private static final int size = 4;
  
  @Expose private int val;
	
	public IntDatum() {
		super(DatumType.INT);
	}
	
	public IntDatum(int val) {
		this();
		this.val = val;
	}

  public IntDatum(byte [] bytes) {
    this();
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    this.val = bb.getInt();
  }

	@Override
	public short asShort() {	
		return (short) val;
	}

  @Override
	public int asInt() {		
		return val;
	}

  @Override
	public long asLong() {
		return val;
	}

  @Override
	public byte asByte() {
		throw new InvalidCastException();
	}

  @Override
	public byte[] asByteArray() {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(val);
		return bb.array();
	}

  @Override
	public float asFloat() {
		return val;
	}

  @Override
	public double asDouble() {
		return val;
	}

  @Override
	public String asChars() {
		return ""+val;
	}

  @Override
	public String toJSON() {
		return GsonCreator.getInstance().toJson(this, Datum.class);
	}

  @Override
  public int size() {
    return size;
  }
  
  @Override
  public int hashCode() {
    return val;
  }
  
  public boolean equals(Object obj) {
    if (obj instanceof IntDatum) {
      IntDatum other = (IntDatum) obj;
      return val == other.val;
    }
    
    return false;
  }

  @Override
  public BoolDatum equalsTo(Datum datum) {
    switch (datum.type()) {
    case SHORT:
      return DatumFactory.createBool(val == datum.asShort());
    case INT:
      return DatumFactory.createBool(val == datum.asInt());
    case LONG:
      return DatumFactory.createBool(val == datum.asLong());
    case FLOAT:
      return DatumFactory.createBool(val == datum.asFloat());
    case DOUBLE:
      return DatumFactory.createBool(val == datum.asDouble());
    default:
      throw new InvalidOperationException();
    }
  }

  @Override
  public int compareTo(Datum datum) {
    switch (datum.type()) {
      case SHORT:
        if (val < datum.asShort()) {
          return -1;
        } else if (datum.asShort() < val) {
          return 1;
        } else {
          return 0;
        }
      case INT:
        if (val < datum.asInt()) {
          return -1;
        } else if (datum.asInt() < val) {
          return 1;
        } else {
          return 0;
        }
      case LONG:
        if (val < datum.asLong()) {
          return -1;
        } else if (datum.asLong() < val) {
          return 1;
        } else {
          return 0;
        }
      case FLOAT:
        if (val < datum.asFloat()) {
          return -1;
        } else if (datum.asFloat() < val) {
          return 1;
        } else {
          return 0;
        }
      case DOUBLE:
        if (val < datum.asDouble()) {
          return -1;
        } else if (datum.asDouble() < val) {
          return 1;
        } else {
          return 0;
        }
      default:
        throw new InvalidOperationException(datum.type());
    }
  }

  @Override
  public Datum plus(Datum datum) {
    switch (datum.type()) {
    case SHORT:
      return DatumFactory.createInt(val + datum.asShort());
    case INT:
      return DatumFactory.createInt(val + datum.asInt());
    case LONG:
      return DatumFactory.createLong(val + datum.asLong());
    case FLOAT:
      return DatumFactory.createFloat(val + datum.asFloat());
    case DOUBLE:
      return DatumFactory.createDouble(val + datum.asDouble());
    default:
      throw new InvalidOperationException(datum.type());
    }
  }

  @Override
  public Datum minus(Datum datum) {
    switch (datum.type()) {
    case SHORT:
      return DatumFactory.createInt(val - datum.asShort());
    case INT:
      return DatumFactory.createInt(val - datum.asInt());
    case LONG:
      return DatumFactory.createLong(val - datum.asLong());
    case FLOAT:
      return DatumFactory.createFloat(val - datum.asFloat());
    case DOUBLE:
      return DatumFactory.createDouble(val - datum.asDouble());
    default:
      throw new InvalidOperationException(datum.type());
    }
  }

  @Override
  public Datum multiply(Datum datum) {
    switch (datum.type()) {
    case SHORT:
      return DatumFactory.createInt(val * datum.asShort());
    case INT:
      return DatumFactory.createInt(val * datum.asInt());
    case LONG:
      return DatumFactory.createLong(val * datum.asLong());
    case FLOAT:
      return DatumFactory.createFloat(val * datum.asFloat());
    case DOUBLE:
      return DatumFactory.createDouble(val * datum.asDouble());
    default:
      throw new InvalidOperationException();
    }
  }

  @Override
  public Datum divide(Datum datum) {
    switch (datum.type()) {
    case SHORT:
      return DatumFactory.createInt(val / datum.asShort());
    case INT:
      return DatumFactory.createInt(val / datum.asInt());
    case LONG:
      return DatumFactory.createLong(val / datum.asLong());
    case FLOAT:
      return DatumFactory.createFloat(val / datum.asFloat());
    case DOUBLE:
      return DatumFactory.createDouble(val / datum.asDouble());
    default:
      throw new InvalidOperationException(datum.type());
    }
  }

  @Override
  public Datum modular(Datum datum) {
    switch (datum.type()) {
      case SHORT:
        return DatumFactory.createInt(val % datum.asShort());
      case INT:
        return DatumFactory.createInt(val % datum.asInt());
      case LONG:
        return DatumFactory.createLong(val % datum.asLong());
      case FLOAT:
        return DatumFactory.createFloat(val % datum.asFloat());
      case DOUBLE:
        return DatumFactory.createDouble(val % datum.asDouble());
      default:
        throw new InvalidOperationException(datum.type());
    }
  }

  @Override
  public void inverseSign() {
    this.val = - val;
  }
}