package com.senijoshua.picsrus.presentation.photodetails


import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.presentation.SharedStates.DETAIL_PHOTO
import com.senijoshua.picsrus.utils.GlideApp
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_photo_details)
class PhotoDetailsFragment : Fragment(){
    @ViewById(R.id.photo_full_screen)
    lateinit var fullScreenView: ImageView

    @ViewById(R.id.photo_detail_info_container)
    lateinit var infoContainer: RelativeLayout

    @ViewById(R.id.photo_detail_user_name)
    lateinit var userNameText: TextView

    @ViewById(R.id.photo_detail_user_social)
    lateinit var userSocialText: TextView

    @ViewById(R.id.photo_detail_likes)
    lateinit var userLikes: TextView

    lateinit var photoUrl: String

    @AfterViews
    fun onViewCreated(){
        mapArgumentValuesToViews()
        GlideApp.with(activity!!)
                .load(photoUrl)
                .listener(requestListener)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(fullScreenView)
        infoContainer.visibility = View.VISIBLE
    }

    var requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
            //Prevent UI hangups even though loadFailed
            parentFragment!!.startPostponedEnterTransition()
            infoContainer.visibility = View.VISIBLE
            return false
        }

        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
            //the transition is postponed until this image finishes loading and when
            //it's done, we start the enter transition that was previously postponed.
            parentFragment!!.startPostponedEnterTransition()
            infoContainer.visibility = View.VISIBLE
            return false
        }
    }

    @SuppressLint("SetTextI18n")
    fun mapArgumentValuesToViews(){
        val photo: Photos = arguments!!.getParcelable(DETAIL_PHOTO)
        val social: String? = photo.user.instagram_username
        val likes: Int? = photo.likes
        photoUrl = photo.urls.regular
        fullScreenView.transitionName = photo.id
        userNameText.text = getString(R.string.username_prefix) + photo.user.name
        userSocialText.text = if (social == null) getString(R.string.no_social_text) else getString(R.string.social_prefix) + photo.user.instagram_username
        userLikes.text = if (likes == 0 || likes == null) getString(R.string.no_likes) else photo.likes.toString()+ getString(R.string.likes_suffix)

    }
}