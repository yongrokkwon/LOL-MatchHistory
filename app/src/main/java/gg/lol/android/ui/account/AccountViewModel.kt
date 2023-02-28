package gg.lol.android.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject internal constructor(

) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _appbarTitle = MutableLiveData<String>()
    val appBarTitle get() = _appbarTitle

    fun setAppBarTitle(value: String) {
        _appbarTitle.value = value
    }

    fun setEmail(value: String) {
        _email.value = value
    }

}