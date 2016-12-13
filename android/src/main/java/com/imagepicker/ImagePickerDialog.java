package com.imagepicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class ImagePickerDialog extends DialogFragment {
    private static final String KEY_TITLES = "titles";
    private static final String KEY_ACTIONS = "actions";
    private static final String KEY_TITLE = "title";

    private SelectionListener listener;

    public static ImagePickerDialog newInstance(
        ArrayList<String> titles,
        ArrayList<String> actions,
        String title,
        SelectionListener listener
    ) {
        ImagePickerDialog f = new ImagePickerDialog();
        Bundle args = new Bundle();
        args.putStringArrayList(KEY_TITLES, titles);
        args.putStringArrayList(KEY_ACTIONS, actions);
        if (title != null) args.putString(KEY_TITLE, title);
        f.setArguments(args);
        f.listener = listener;
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = this.getArguments();
        final ArrayList<String> titles = args.getStringArrayList(KEY_TITLES);
        final ArrayList<String> actions = args.getStringArrayList(KEY_ACTIONS);
        String title = args.getString(KEY_TITLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
            android.R.layout.select_dialog_item, titles);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        if (title != null) builder.setTitle(title);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int index) {
                if (ImagePickerDialog.this.listener != null)
                    ImagePickerDialog.this.listener.onSelected(actions.get(index));
            }
        });
        return builder.create();
    }

    public interface SelectionListener {
        void onSelected(String action);
    }
}
