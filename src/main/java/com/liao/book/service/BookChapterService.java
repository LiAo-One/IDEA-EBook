package com.liao.book.service;

import cn.hutool.http.HttpUtil;
import com.liao.book.entity.Chapter;
import com.liao.book.entity.DataCenter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

/**
 * <p>
 * 爬取章节
 * </p>
 *
 * @author LiAo
 * @since 2021/1/14
 */
public class BookChapterService {

    /**
     * 笔趣阁书籍爬取
     *
     * @param link 链接
     */
    public static void searchBookChapterData(String link) {

        // String url = "http://www.xbiquge.la/25/25430/";
        DataCenter.chapters.clear();
        String result1 = HttpUtil.get(link);
        try {
            Document parse = Jsoup.parse(result1);
            Elements grid = parse.getElementsByTag("dd");

            for (Element element : grid) {
                Chapter chapter = new Chapter();
                // 链接
                String attr = element.getElementsByTag("a").eq(0).attr("href");
                // 名称
                String name = element.getElementsByTag("a").eq(0).text();

                chapter.setName(name);
                chapter.setLink("https://www.xbiquge.la/" + attr);

                DataCenter.chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 妙笔阁书籍爬取
     *
     * @param link 链接
     */
    public static void searchBookChapterData_miao(String link) {

        DataCenter.chapters.clear();

        String result1 = HttpUtil.get(link);
        try {
            Document parse = Jsoup.parse(result1);
            Elements border_line = parse.getElementsByClass("border-line");
            Iterator it = border_line.iterator();
            it.next();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                Elements grid = element.parent().getElementsByTag("li");
                for (Element element2 : grid) {
                    Chapter chapter = new Chapter();

                    // 链接
                    String attr = element2.getElementsByTag("a").eq(0).attr("href");
                    // 名称
                    String name = element2.getElementsByTag("a").eq(0).text();

                    chapter.setName(name);
                    chapter.setLink("https://www.imiaobige.com/" + attr);
                    if (attr.contains("read")) {
                        DataCenter.chapters.add(chapter);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 全本小说网书籍爬取
     *
     * @param link 链接
     */
    public static void searchBookChapterData_tai(String link) {

        DataCenter.chapters.clear();

        String result1 = HttpUtil.get(link);
        try {
            Document parse = Jsoup.parse(result1);
            Elements grid = parse.getElementsByTag("li");

            for (Element element : grid) {
                Chapter chapter = new Chapter();
                // 链接
                String attr = element.getElementsByTag("a").eq(0).attr("href");
                // 名称
                String name = element.getElementsByTag("a").eq(0).text();

                chapter.setName(name);
                chapter.setLink(link + attr);

                DataCenter.chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 笔趣阁书籍爬取
     *
     * @param link 链接
     */
    public static void searchBookChapterData_bqg2(String link) {

        DataCenter.chapters.clear();

        String result1 = HttpUtil.get(link);
        try {
            Document parse = Jsoup.parse(result1);
            Elements grid = parse.getElementsByTag("dd");

            for (Element element : grid) {
                Chapter chapter = new Chapter();
                // 链接
                String attr = element.getElementsByTag("a").eq(0).attr("href");
                // 名称
                String name = element.getElementsByTag("a").eq(0).text();

                chapter.setName(name);
                chapter.setLink("https://www.biduoxs.com" + attr);

                DataCenter.chapters.add(chapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
