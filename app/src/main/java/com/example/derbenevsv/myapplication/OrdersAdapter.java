package com.example.derbenevsv.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.derbenevsv.myapplication.OrdersFragment.OnOrderClickListener;
import com.example.derbenevsv.myapplication.api_1c.Entitys.OrderEntity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OrderEntity} and makes a call to the
 * specified {@link OnOrderClickListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder>
{

    private final OnOrderClickListener mListener;
    private List<OrderEntity> mValues;

    public OrdersAdapter(List<OrderEntity> orders, OnOrderClickListener listener)
    {
        mValues = orders;
        mListener = listener;
    }

    public void SetOrders(List<OrderEntity> orders)
    {
        mValues = orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mItem = mValues.get(position);
        holder.mNumberView.setText(mValues.get(position)
                .getOrderNumber());
        holder.mOrderDateView.setText(mValues.get(position)
                .getOrderDate());

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener)
                {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final TextView mNumberView;
        public final TextView mOrderDateView;
        public OrderEntity mItem;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            String guid;
            mNumberView = (TextView) view.findViewById(R.id.orderNumber);
            mOrderDateView = (TextView) view.findViewById(R.id.orderDate);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mNumberView.getText() + " от " + mOrderDateView.getText() + "'";
        }
    }
}
