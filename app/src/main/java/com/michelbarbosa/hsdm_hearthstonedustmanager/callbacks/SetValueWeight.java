package com.michelbarbosa.hsdm_hearthstonedustmanager.callbacks;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.SetWeight;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.TypeWeight;

public interface SetValueWeight {
    void callbackValueSpinnerSelect(SetWeight setWeight);
    void callbackValueSpinnerSelect(TypeWeight typeWeight);
}

