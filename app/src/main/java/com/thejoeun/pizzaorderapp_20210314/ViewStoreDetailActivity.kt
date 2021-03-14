package com.thejoeun.pizzaorderapp_20210314

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.thejoeun.pizzaorderapp_20210314.datas.Store
import kotlinx.android.synthetic.main.activity_view_store_detail.*
import java.security.Permission

class ViewStoreDetailActivity : BaseActivity() {

    lateinit var mStoreData : Store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_store_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {    //버튼이 눌려서 발생되는 작업

        orderCallBtn.setOnClickListener {

            //매니페스트 권한, build 라이브러리 추가
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {

                    val myUri = Uri.parse("tel:${mStoreData.phoneNum}")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(mContext, "권한이 거부되어 통화가 불가능합니다.", Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.with(mContext)
                    .setPermissionListener(pl)
                    .setDeniedMessage("[설정]에서 통화 권한을 허락해주세요")
                    .setPermissions(Manifest.permission.CALL_PHONE)
                    .check()

        }

    }

    override fun setValues() {

        mStoreData = intent.getSerializableExtra("store") as Store

        nameTxt.text = mStoreData.storeName
        phoneNumTxt.text = mStoreData.phoneNum

        Glide.with(mContext).load(mStoreData.logoUri).into(logoImg)

    }


}