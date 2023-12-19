package org.example;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class MyRecord extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = 4189293724650899117L;
    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MyRecord\",\"fields\":[{\"name\":\"f1\",\"type\":\"string\"},{\"name\":\"f2\",\"type\":\"int\"},{\"name\":\"f3\",\"type\":\"boolean\"}]}");
    public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

    private static SpecificData MODEL$ = new SpecificData();

    private static final BinaryMessageEncoder<MyRecord> ENCODER =
            new BinaryMessageEncoder<MyRecord>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<MyRecord> DECODER =
            new BinaryMessageDecoder<MyRecord>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<MyRecord> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<MyRecord> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<MyRecord> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<MyRecord>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this MyRecord to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a MyRecord from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a MyRecord instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static MyRecord fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private java.lang.CharSequence f1;
    private int f2;
    private boolean f3;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public MyRecord() {}

    /**
     * All-args constructor.
     * @param f1 The new value for f1
     * @param f2 The new value for f2
     * @param f3 The new value for f3
     */
    public MyRecord(java.lang.CharSequence f1, java.lang.Integer f2, java.lang.Boolean f3) {
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
    }

    public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
    public org.apache.avro.Schema getSchema() { return SCHEMA$; }
    // Used by DatumWriter.  Applications should not call.
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0: return f1;
            case 1: return f2;
            case 2: return f3;
            default: throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    // Used by DatumReader.  Applications should not call.
    @SuppressWarnings(value="unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0: f1 = (java.lang.CharSequence)value$; break;
            case 1: f2 = (java.lang.Integer)value$; break;
            case 2: f3 = (java.lang.Boolean)value$; break;
            default: throw new org.apache.avro.AvroRuntimeException("Bad index");
        }
    }

    /**
     * Gets the value of the 'f1' field.
     * @return The value of the 'f1' field.
     */
    public java.lang.CharSequence getF1() {
        return f1;
    }


    /**
     * Sets the value of the 'f1' field.
     * @param value the value to set.
     */
    public void setF1(java.lang.CharSequence value) {
        this.f1 = value;
    }

    /**
     * Gets the value of the 'f2' field.
     * @return The value of the 'f2' field.
     */
    public int getF2() {
        return f2;
    }


    /**
     * Sets the value of the 'f2' field.
     * @param value the value to set.
     */
    public void setF2(int value) {
        this.f2 = value;
    }

    /**
     * Gets the value of the 'f3' field.
     * @return The value of the 'f3' field.
     */
    public boolean getF3() {
        return f3;
    }


    /**
     * Sets the value of the 'f3' field.
     * @param value the value to set.
     */
    public void setF3(boolean value) {
        this.f3 = value;
    }

    /**
     * Creates a new MyRecord RecordBuilder.
     * @return A new MyRecord RecordBuilder
     */
    public static MyRecord.Builder newBuilder() {
        return new MyRecord.Builder();
    }

    /**
     * Creates a new MyRecord RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new MyRecord RecordBuilder
     */
    public static MyRecord.Builder newBuilder(MyRecord.Builder other) {
        if (other == null) {
            return new MyRecord.Builder();
        } else {
            return new MyRecord.Builder(other);
        }
    }

    /**
     * Creates a new MyRecord RecordBuilder by copying an existing MyRecord instance.
     * @param other The existing instance to copy.
     * @return A new MyRecord RecordBuilder
     */
    public static MyRecord.Builder newBuilder(MyRecord other) {
        if (other == null) {
            return new MyRecord.Builder();
        } else {
            return new MyRecord.Builder(other);
        }
    }

    /**
     * RecordBuilder for MyRecord instances.
     */
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MyRecord>
            implements org.apache.avro.data.RecordBuilder<MyRecord> {

        private java.lang.CharSequence f1;
        private int f2;
        private boolean f3;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(MyRecord.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.f1)) {
                this.f1 = data().deepCopy(fields()[0].schema(), other.f1);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.f2)) {
                this.f2 = data().deepCopy(fields()[1].schema(), other.f2);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.f3)) {
                this.f3 = data().deepCopy(fields()[2].schema(), other.f3);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
        }

        /**
         * Creates a Builder by copying an existing MyRecord instance
         * @param other The existing instance to copy.
         */
        private Builder(MyRecord other) {
            super(SCHEMA$);
            if (isValidValue(fields()[0], other.f1)) {
                this.f1 = data().deepCopy(fields()[0].schema(), other.f1);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.f2)) {
                this.f2 = data().deepCopy(fields()[1].schema(), other.f2);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.f3)) {
                this.f3 = data().deepCopy(fields()[2].schema(), other.f3);
                fieldSetFlags()[2] = true;
            }
        }

        /**
         * Gets the value of the 'f1' field.
         * @return The value.
         */
        public java.lang.CharSequence getF1() {
            return f1;
        }


        /**
         * Sets the value of the 'f1' field.
         * @param value The value of 'f1'.
         * @return This builder.
         */
        public MyRecord.Builder setF1(java.lang.CharSequence value) {
            validate(fields()[0], value);
            this.f1 = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'f1' field has been set.
         * @return True if the 'f1' field has been set, false otherwise.
         */
        public boolean hasF1() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'f1' field.
         * @return This builder.
         */
        public MyRecord.Builder clearF1() {
            f1 = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'f2' field.
         * @return The value.
         */
        public int getF2() {
            return f2;
        }


        /**
         * Sets the value of the 'f2' field.
         * @param value The value of 'f2'.
         * @return This builder.
         */
        public MyRecord.Builder setF2(int value) {
            validate(fields()[1], value);
            this.f2 = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'f2' field has been set.
         * @return True if the 'f2' field has been set, false otherwise.
         */
        public boolean hasF2() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'f2' field.
         * @return This builder.
         */
        public MyRecord.Builder clearF2() {
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'f3' field.
         * @return The value.
         */
        public boolean getF3() {
            return f3;
        }


        /**
         * Sets the value of the 'f3' field.
         * @param value The value of 'f3'.
         * @return This builder.
         */
        public MyRecord.Builder setF3(boolean value) {
            validate(fields()[2], value);
            this.f3 = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'f3' field has been set.
         * @return True if the 'f3' field has been set, false otherwise.
         */
        public boolean hasF3() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'f3' field.
         * @return This builder.
         */
        public MyRecord.Builder clearF3() {
            fieldSetFlags()[2] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public MyRecord build() {
            try {
                MyRecord record = new MyRecord();
                record.f1 = fieldSetFlags()[0] ? this.f1 : (java.lang.CharSequence) defaultValue(fields()[0]);
                record.f2 = fieldSetFlags()[1] ? this.f2 : (java.lang.Integer) defaultValue(fields()[1]);
                record.f3 = fieldSetFlags()[2] ? this.f3 : (java.lang.Boolean) defaultValue(fields()[2]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<MyRecord>
            WRITER$ = (org.apache.avro.io.DatumWriter<MyRecord>)MODEL$.createDatumWriter(SCHEMA$);

    @Override public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<MyRecord>
            READER$ = (org.apache.avro.io.DatumReader<MyRecord>)MODEL$.createDatumReader(SCHEMA$);

    @Override public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override protected boolean hasCustomCoders() { return true; }

    @Override public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException
    {
        out.writeString(this.f1);

        out.writeInt(this.f2);

        out.writeBoolean(this.f3);

    }

    @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException
    {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.f1 = in.readString(this.f1 instanceof Utf8 ? (Utf8)this.f1 : null);

            this.f2 = in.readInt();

            this.f3 = in.readBoolean();

        } else {
            for (int i = 0; i < 3; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.f1 = in.readString(this.f1 instanceof Utf8 ? (Utf8)this.f1 : null);
                        break;

                    case 1:
                        this.f2 = in.readInt();
                        break;

                    case 2:
                        this.f3 = in.readBoolean();
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }
}


