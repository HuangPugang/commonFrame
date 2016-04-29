package net.maker.commonframework.base.rx.buluetooth;

import android.bluetooth.BluetoothProfile;

/**
 * Created by MakerYan on 16/4/9 22:55.
 * Email : yanl@59store.com
 * Personal e-mail : yl_flash@163.com
 * project name : CommonFramework
 * package name : net.maker.commonframework.base.rx.buluetooth
 */
public class ServiceEvent {
    private State mState;
    private int mProfileType;
    private BluetoothProfile mBluetoothProfile;
    public ServiceEvent(State state, int profileType, BluetoothProfile bluetoothProfile) {
        mState = state;
        mProfileType = profileType;
        mBluetoothProfile = bluetoothProfile;
    }

    public State getState() {
        return mState;
    }

    public int getProfileType() {
        return mProfileType;
    }

    public BluetoothProfile getBluetoothProfile() {
        return mBluetoothProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEvent that = (ServiceEvent) o;

        if (mProfileType != that.mProfileType) return false;
        if (mState != that.mState) return false;
        return !(mBluetoothProfile != null ? !mBluetoothProfile.equals(that.mBluetoothProfile)
                : that.mBluetoothProfile != null);
    }

    @Override
    public int hashCode() {
        int result = mState.hashCode();
        result = 31 * result + mProfileType;
        result = 31 * result + (mBluetoothProfile != null ? mBluetoothProfile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceEvent{" +
                "mState=" + mState +
                ", mProfileType=" + mProfileType +
                ", mBluetoothProfile=" + mBluetoothProfile +
                '}';
    }

    public enum State {
        CONNECTED,
        DISCONNECTED
    }
}
