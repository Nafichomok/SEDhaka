package com.example.stockpublic;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class CollectInfo extends AsyncTask<Void, Void, Void> {
    @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        private Context context;
        private ArrayList<Company> companyList;
        private ListAdapter adapter;
        private ListView lv;
         private boolean size;
         int counter=0;
         String url;

    CollectInfo(ArrayList<Company> companyList, Context context, ListAdapter adapter, boolean size, ListView lv) {
            this.companyList = companyList;
            this.context=context;
            this.adapter=adapter;
            this.size = size;
            this.lv = lv;
            if(size){
                url = "https://dsebd.org/latest_share_price_all_by_ltp.php";
            }
            else{
                url ="http://dsebd.org/latest_share_price_all.php";
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                companyList.clear();
            } catch (Exception e) {

            }
            try {
                Document company = null;
                try {
                        company = Jsoup.connect(url).get();

                } catch (SocketTimeoutException e) {
                    String d=e.getStackTrace().toString();
                    System.out.println(d);
                }
                Elements importantLinks = company.select("tr");
                boolean first = true;
                for (Element data : importantLinks) {

                    if (!first) {
                        String[] d = data.text().split(" ");
                        String code = d[1];
                        double ltp;
                        try {
                            ltp = Double.parseDouble(d[2].replace(",", ""));
                        } catch (Exception a) {
                            ltp = 0;
                        }
                        double high;
                        try {
                            high = Double.parseDouble(d[3].replace(",", ""));
                        } catch (Exception b) {
                            high = 0;
                        }

                        double low;
                        try {
                            low = Double.parseDouble(d[4].replace(",", ""));
                        } catch (Exception c) {
                            low = 0;
                        }

                        double closep;
                        try {
                            closep = Double.parseDouble(d[5].replace(",", ""));
                        } catch (Exception de) {
                            closep = 0;
                        }

                        double ycp;
                        try {
                            ycp = Double.parseDouble(d[6].replace(",", ""));
                        } catch (Exception e) {
                            ycp = 0;
                        }
                        double change;
                        try {
                            change = Double.parseDouble(d[7].replace(",", ""));
                        } catch (Exception f) {
                            change = 0;
                        }
                        double changeP;
                        if (ltp > 0)
                            changeP = Double.parseDouble(String.format("%.2f", (((ltp - ycp) * 100) / ycp)));
                        else changeP = 0;
                        double trade;
                        try {
                            trade = Double.parseDouble(d[8].replace(",", ""));
                        } catch (Exception g) {
                            trade = 0;
                        }

                        double value;
                        try {
                            value = Double.parseDouble(d[9].replace(",", ""));
                        } catch (Exception j) {
                            value = 0;
                        }
                        double volume;
                        try {
                            volume = Double.parseDouble(d[10].replace(",", ""));
                        } catch (Exception h) {
                            volume = 0;
                        }
                        if(!size)
                        companyList.add(new Company(code, ltp, high, low, closep, ycp, change, changeP, trade, value, volume));
                        else if(size&& counter<20){
                            companyList.add(new Company(code, ltp, high, low, closep, ycp, change, changeP, trade, value, volume));
                            counter++;
                        }

                    }

                        first = false;

                }


                company = Jsoup.connect("http://www.dsebd.org/company%20listing.php").get();

                importantLinks = company.getElementsByTag("font");
                for (Element link : importantLinks) {
                    if (link.text().toString().contains("(")) {
                        String linkText = link.text().toString().substring((link.text().toString().indexOf(' ') + 2), link.text().toString().lastIndexOf(')'));
                        String code = link.text().toString().substring(0, link.text().toString().indexOf(' '));

                        //System.out.println(linkText+"+"+code);//THIS IS THE TEXT I WANTED...
                        if (code.toUpperCase().equals(code) && !code.contains("T20") && !code.contains("T05") && !code.contains("T10") && !code.contains("T15") && !code.contains("T5") && code.length() > 1) {
                            String name = linkText;
                            //System.out.println(name);
                            for (Company a : companyList) {
                                if (a.getCode().toString().equals(code.toString())) {
                                    a.setName((name));
                                    //System.out.println("Company Name: "+a.getName()+"\nCompany Code:"+a.getCode());
                                    break;
                                }
                            }

                            //companyList.add(new Company(name, ltp, change, changeP));
                            //System.out.println(name);

                        }


                    }

                }

            } catch (Exception e) {
                String d=e.getStackTrace().toString();
                System.out.println(d);
            }

            return null;
        }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try{
            adapter=new ListAdapter(context,R.layout.adapter_view,companyList);
            lv.setAdapter(adapter);
            System.out.println("Hello");

        }catch (Exception e){

        }

    }
}