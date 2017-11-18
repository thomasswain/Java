

/**
 * Implementation of the Sound interface to produce sine-wave pure tones at
 * specific frequencies based on the piano
 * 
 * @author acr31
 */
public class SineWaveSound implements Sound {

	private static double piano[] = {82.4069, 87.3071, 92.4986, 97.9989, 103.826, 110, 116.541, 123.471, 130.813,
										138.591, 146.832, 155.563, 164.814, 174.614, 184.997, 195.998, 207.652, 220,
										233.082, 246.942, 261.626, 277.183, 293.665, 311.127, 329.628, 349.228,
										369.994, 391.995, 415.305, 440, 466.164, 493.883, 523.251, 554.365, 587.33,
										622.254, 659.255, 698.456, 739.989, 783.991, 830.609, 880, 932.328, 987.767,
										1046.5, 1108.73, 1174.66, 1244.51, 1318.51, 1396.91, 1479.98, 1567.98, 1661.22,
										1760, 1864.66, 1975.53, 2093, 2217.46, 2349.32, 2489.02, 2637.02, 2793.83,
										2959.96, 3135.96, 3322.44, 3520, 3729.31, 3951.07, 4186.01 };

	/**
	 * The number of samples for which to fade in and out. Sudden transitions
	 * from a high amplitude to zero at the end of a timeslot would introduce
	 * high-frequency components (clicks and pops) into the audio stream, so we
	 * simply fade in and out.
	 */
	public static final int RAMPUP_SAMPLES = 100;

	private double frequency;
	private int amplitude;
	
	/**
	 * Create a SineWaveSound
	 * 
	 * @param frequencyScale
	 *            should be a value between 0 (lowest) and 1 (highest) pitch
	 */
	public SineWaveSound(double frequencyScale, double amplitudeScale) {
		int index = (int) Math.round(piano.length * frequencyScale);
		if (index >= piano.length) index = piano.length - 1;
		if (index < 0) index = 0;
		this.frequency = piano[index];
		this.amplitude = (int)Math.round(Short.MAX_VALUE * amplitudeScale);
	}

	/**
	 * Compute the tone value for a particular point in the sample
	 * 
	 * @param sample
	 * @return
	 */
	protected double compute(int sample) {
		double v = Math.sin((double) sample * 2.0 * Math.PI * (double) frequency
				/ (double) AudioSequence.SAMPLES_PER_SECOND);
		return v;
	}

	/**
	 * Adds this tone to the data already playing for this timeslot
	 * 
	 * @param data the array of samples for this timeslot
	 * @throws SoundOverflowException if the data value overflows the size of a short
	 */
	@Override
	public void addToSamples(short[] data) throws SoundOverflowException {
		for (int i = 0; i < data.length; ++i) {
			double scale = 1.0;
			if (i < RAMPUP_SAMPLES) {
				scale = (double) i / (double) RAMPUP_SAMPLES;
			}
			else if (i > data.length - RAMPUP_SAMPLES) {
				scale = (double) (data.length - i) / (double) RAMPUP_SAMPLES;
			}

			double v = compute(i) * scale * amplitude;
			
			if (Short.MAX_VALUE - v < data[i]) { 
				throw new SoundOverflowException();
			}
			
			if (Short.MIN_VALUE + v > data[i]) {
				throw new SoundOverflowException();
			}
			data[i] += Math.round(v);
		}
	}

}
