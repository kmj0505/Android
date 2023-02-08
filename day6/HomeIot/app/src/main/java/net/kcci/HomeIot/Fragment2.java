package net.kcci.HomeIot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import net.kcci.HomeIot.R;

import org.w3c.dom.Text;

public class Fragment2 extends Fragment {

    MainActivity mainActivity;
    TextView textViewTemp;
    TextView textViewIllumi;
    TextView textViewHumi;

    ImageView imageledoff;
    ImageView imageblindsoff;
    ImageView imageairoff;

    Switch switchLed;
    Switch switchBlinds;
    Switch switchAir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        mainActivity = (MainActivity)getActivity();

        textViewTemp = view.findViewById(R.id.textViewTemp);
        textViewIllumi = view.findViewById(R.id.textViewIllumi);
        textViewHumi = view.findViewById(R.id.textViewHumi);

        switchLed = view.findViewById(R.id.switchLed);
        switchBlinds = view.findViewById(R.id.switchBlinds);
        switchAir = view.findViewById(R.id.switchAir);

        imageledoff = view.findViewById(R.id.imageledoff);
        imageblindsoff = view.findViewById(R.id.imageblindsoff);
        imageairoff = view.findViewById(R.id.imageairoff);

        Button buttonCondition = view.findViewById(R.id.buttonCondition);
        Button buttonControl = view.findViewById(R.id.buttonControl);
        buttonCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.clientThread.sendData("[KMJ_ARD]GETSENSOR\n");
            }
        });


        buttonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        switchLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchLed.isChecked()){
                        mainActivity.clientThread.sendData("[KMJ_ARD]LEDON\n");
                        switchLed.setChecked(false);
                    }
                    else{
                        mainActivity.clientThread.sendData("[KMJ_ARD]LEDOFF\n");
                        switchLed.setChecked(true);
                    }
                }
            }
        });


        switchBlinds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchBlinds.isChecked()){
                        mainActivity.clientThread.sendData("[KMJ_ARD]BLINDSON\n");
                        switchBlinds.setChecked(false);
                    }
                    else {
                        mainActivity.clientThread.sendData("[KMJ_ARD]BLINDSOFF\n");
                        switchBlinds.setChecked(true);
                    }
                }
            }
        });


        switchAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClientThread.socket != null){
                    if(switchAir.isChecked()){
                        mainActivity.clientThread.sendData("[KMJ_ARD]AIRON\n");
                        switchAir.setChecked(false);
                    }
                    else{
                        mainActivity.clientThread.sendData("[KMJ_ARD]AIROFF\n");
                        switchAir.setChecked(true);
                    }
                }
            }
        });
        return view;
    }

    void recvDataProcess(String strRecvData)
    {
        String[] splitLists = strRecvData.toString().split("\\[|]|@|\\r");      //"[" 또는 "]" 또는 "@" 또는 "\r" 단위로 분리하겠다
        for(int i=0; i<splitLists.length; i++){
            Log.d("recvDataProcess", "i : " + i + " , value: " + splitLists[i]);
        }
        if(splitLists[2].equals("LEDON")){
            imageledoff.setImageResource(R.drawable.led_on);
            switchLed.setChecked(true);
        }
        else if(splitLists[2].equals("LEDOFF")){
            imageledoff.setImageResource(R.drawable.led_off);
            switchLed.setChecked(false);

        }
        else if(splitLists[2].equals("BLINDSON")){
            imageblindsoff.setImageResource(R.drawable.blinds_on);
            switchBlinds.setChecked(true);

        }
        else if(splitLists[2].equals("BLINDSOFF")){
            imageblindsoff.setImageResource(R.drawable.blinds_off);
            switchBlinds.setChecked(false);

        }
        else if(splitLists[2].equals("AIRON")){
            imageairoff.setImageResource(R.drawable.air_on);
            switchAir.setChecked(true);

        }
        else if(splitLists[2].equals("AIROFF")){
            imageairoff.setImageResource(R.drawable.air_off);
            switchAir.setChecked(false);
        }
        else if(splitLists[2].equals("SENSOR")){
            textViewIllumi.setText("");
            textViewIllumi.append(splitLists[3]+"%");
            textViewTemp.setText("");
            textViewTemp.append(splitLists[4]+"℃");
            textViewHumi.setText("");
            textViewHumi.append(splitLists[5]+"%");
        }
    }

}
