package com.example.derbenevsv.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.derbenevsv.myapplication.api_1c.Entitys.OrderEntity;
import com.example.derbenevsv.myapplication.api_1c.Responses.GetOrdersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class OrdersFragment extends Fragment implements Callback
{

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    RecyclerView recyclerView;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnOrderClickListener mListener;
    private OrdersAdapter ordersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        // Set the adapter

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rvOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(ordersAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnOrderClickListener)
        {
            mListener = (OnOrderClickListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnOrderClickListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResponse(Call call, Response response)
    {
        if (response.isSuccessful())
        {
            List<OrderEntity> orders;
            if (response.body() != null)
            {
                orders = ((GetOrdersResponse) response.body()).getOrders();
                if (ordersAdapter == null)
                {
                    ordersAdapter = new OrdersAdapter(orders, mListener);
                    recyclerView.setAdapter(ordersAdapter);
                }
                else
                {
                    ordersAdapter.SetOrders(orders);
                }

                ordersAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onFailure(Call call, Throwable t)
    {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnOrderClickListener
    {
        // TODO: Update argument type and name
        void onListFragmentInteraction(OrderEntity item);
    }
}
