package com.jmedinilla.asyncbubblesort;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;

public class HiddenFragment extends Fragment {
    private ITaskCallback taskCallback;

    interface ITaskCallback {
        void onPreExecute();

        void onProgressUpdate(int progress);

        void onCancelled();

        void onPostExecute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            taskCallback = (ITaskCallback) activity;
        } catch (ClassCastException exc) {
            throw new ClassCastException(activity.getLocalClassName() + " must implements ITaskCallback interface");
        }
    }

    @Override
    public void onDetach() {
        taskCallback = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        BubbleNumberTask bubbleNumberTask = new BubbleNumberTask();
        bubbleNumberTask.execute();
    }

    class BubbleNumberTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            taskCallback.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            taskCallback.onProgressUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            taskCallback.onPostExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            taskCallback.onCancelled();
        }
    }
}
