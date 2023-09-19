package com.mutsuddi_s.mypracticebs.ui.features.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.mutsuddi_s.mypracticebs.data.entitiies.Character
import com.mutsuddi_s.mypracticebs.databinding.ItemCharacterBinding


class CharacterAdapter(private val listener: OnItemClickListener): ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffUtilCallBack())
{

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }
    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Character) {
            binding.name.text = item.name
            binding.speciesAndStatus.text = """${item.species} - ${item.status}"""
           /* when (item.status) {
                "Alive" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_green)
                "unknown" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_gray)
                "Dead" -> binding.status.setBackgroundResource(R.drawable.ic_status_circle_red)

            }*/
            val imageLink = item.image
           /* binding.image.load(imageLink) {
                crossfade(true)
                crossfade(1000)
            }*/
            Glide.with(binding.root)
                .load(item.image)
                .transform(CircleCrop())
                .into(binding.image)
            binding.root.setOnClickListener {
                // Invoke the callback method with the clicked item
                listener.onItemClick(item.id)
            }
           /* binding.root.setOnClickListener {
                // Invoke the callback method with the clicked item
                listener.onItemClick(item)
            }*/
        }
    }

    class DiffUtilCallBack:androidx.recyclerview.widget.DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character
        ): Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        //val item=getItem(position)
        // holder.bind(item)
       // holder.name.text=item.content
       // holder.initial.text=item.author

        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

}