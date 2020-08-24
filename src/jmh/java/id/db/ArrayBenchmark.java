package id.db;

import org.openjdk.jmh.annotations.*;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Timeout(time = 5)
public class ArrayBenchmark
{
  @Param({"16", "32", "64", "128", "256", "512"})
  public int size;

  private long[] longArray;
  private byte[] byteArray;
  private ByteBuffer byteBuffer;

  public ArrayBenchmark()
  {
    // Empty
  }

  @Setup(Level.Iteration)
  public void setup()
  {
    longArray = new long[size];
    byteArray = new byte[size * 8];

    for (int i = 0; i < size; i++) {
      longArray[i] = 0x1e101365_77081f4dL;
    }
    for (int i = 0; i < size; i++) {
      byteArray[8 * i] = 0x1e;
      byteArray[8 * i + 1] = 0x10;
      byteArray[8 * i + 2] = 0x13;
      byteArray[8 * i + 3] = 0x65;
      byteArray[8 * i + 4] = 0x77;
      byteArray[8 * i + 5] = 0x08;
      byteArray[8 * i + 6] = 0x1f;
      byteArray[8 * i + 7] = 0x4d;
    }

    byteBuffer = ByteBuffer.wrap(byteArray);
  }

  @Benchmark
  @Warmup(time = 10, iterations = 1)
  @Measurement(time = 10, iterations = 3)
  public long benchmarkLongArray()
  {
    long checksum = 0;
    for (long l: longArray) {
      checksum ^= l;
    }
    return checksum;
  }

  @Benchmark
  @Warmup(time = 10, iterations = 1)
  @Measurement(time = 10, iterations = 3)
  public long benchmarkByteArray()
  {
    long checksum = 0;
    for (long b: byteArray) {
      checksum ^= b;
    }
    return checksum;
  }

  @Benchmark
  @Warmup(time = 10, iterations = 1)
  @Measurement(time = 10, iterations = 3)
  public long benchmarkByteToLong()
  {
    long checksum = 0;
    for (int i = 0; i < byteArray.length; i += 8) {
      checksum ^= XxHash64.getLong(byteArray, i);
    }
    return checksum;
  }

  @Benchmark
  @Warmup(time = 10, iterations = 1)
  @Measurement(time = 10, iterations = 3)
  public long benchmarkByteBuffer()
  {
    byteBuffer.rewind();
    long checksum = 0;
    for (int i = 0; i < size; i++) {
      checksum ^= byteBuffer.getLong();
    }
    return checksum;
  }
}
