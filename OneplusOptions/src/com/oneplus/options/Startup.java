/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package com.oneplus.options;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.oneplus.options.settings.DCIModeSwitch;
import com.oneplus.options.settings.SRGBModeSwitch;
import com.oneplus.options.settings.TapToWakeSwitch;
import com.oneplus.options.settings.VibratorStrengthPreference ;
import com.oneplus.options.settings.OptionPanelSettings;
import com.oneplus.options.util.Utils;

public class Startup extends BroadcastReceiver {

    private void restore(String file, boolean enabled) {
        if (file == null) {
            return;
        }
        if (enabled) {
            Utils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {
        if (file == null) {
            return;
        }
        Utils.writeValue(file, value);
    }

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
		
        boolean enabled = sharedPrefs.getBoolean(OptionPanelSettings.KEY_SRGB_SWITCH, false);
        restore(SRGBModeSwitch.getFile(), enabled);

        enabled = sharedPrefs.getBoolean(OptionPanelSettings.KEY_TAPTOWAKE_SWITCH, false);
        restore(TapToWakeSwitch.getFile(), enabled);

        enabled = sharedPrefs.getBoolean(OptionPanelSettings.KEY_DCI_SWITCH, false);
        restore(DCIModeSwitch.getFile(), enabled);

        VibratorStrengthPreference.restore(context);
    }
}

