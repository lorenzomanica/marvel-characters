package br.pro.lmit.marvelcharacters.ui.characterlist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.pro.lmit.marvelcharacters.data.entity.Character
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Picasso.get().load(url).into(view)
}

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<Character>) {
    (listView.adapter as CharacterAdapter).submitList(items)
}
