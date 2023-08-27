package com.mbds.tpt_sarino_brian_hery.viewmodel.declareevent;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.mbds.tpt_sarino_brian_hery.model.declareevent.DeclareEvent;
import com.mbds.tpt_sarino_brian_hery.model.declareevent.DeclareEventServiceLocal;

import java.util.List;

public class DeclareEventViewModel extends ViewModel {
    private MutableLiveData<List<DeclareEvent>> declareEventLiveData;
    private DeclareEventServiceLocal declareEventServiceLocal;


    private static final String DATABASE_NAME = "tpt_sarino_brian_hery";
    private static final int DATABASE_VERSION = 1;
    public DeclareEventViewModel(Context context) {
        declareEventServiceLocal = new DeclareEventServiceLocal(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Rafréchir la liste des evenements.
     */
    public void refresh() {
        declareEventLiveData.setValue(declareEventServiceLocal.findAll());
    }

    /**
     * Obtenir la liste des evenements.
     * @return
     */
    public LiveData<List<DeclareEvent>> findAll() {
        if (declareEventLiveData == null) {
            declareEventLiveData = new MutableLiveData<>();
            refresh();
        }
        return declareEventLiveData;
    }

    /**
     * Ajouter un evenement.
     */
    public DeclareEvent add(DeclareEvent event) {

        event = declareEventServiceLocal.addDeclareEvent(event);

        if (event != null) {
            refresh();
            return event;
        } else {
            return null;
        }
    }

    /**
     *  Obtenir un evenement à partir de son id.
     */
    public DeclareEvent findById(int id) {
        return declareEventServiceLocal.findById(id);
    }
}
