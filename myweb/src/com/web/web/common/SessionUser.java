package com.web.web.common;

import com.web.core.utility.Setting;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * Created by Edmund on 2015/7/13.
 */
public class SessionUser implements Serializable
{
    private final static String externalPhoto = Setting.getSetting("externalPhoto");

    private long userId;
    private String username;
    private String nativeName;
    private String telephone;
    private String email;
    private String field;
    private String portraitUrl;
    private String company;
    private String title;
    private String userType;
    private String identity;
    private int openProjects;
    private int joinProjects;
    private int listProjects;
    private int earnedScore;
    private int appliedTasks;               // �������������
    private int involvedTasks;              // �Ѳ����������
    private int finishedTasks;              // ����ɵ�������
    private int interviews;                 // Լ̸�����ô�ҵ��������ĿԼ̸��֮�ͣ�
    private int verifications;              // �Ѿ���õ���֤��

    private int messages;
    private int notices;
    private int state;

    public long getUserId()
    {
        return  userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getNativeName()
    {
        return nativeName;
    }

    public void setNativeName(String nativeName)
    {
        this.nativeName = nativeName;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getField()
    {
        return field;
    }

    public void setField(String field)
    {
        this.field = field;
    }

    public String getIcon()
    {
        if (StringUtils.isEmpty(portraitUrl))
        {
            return "";
        }

        return StringUtils.substringBeforeLast(portraitUrl, ".") + "_icon." + StringUtils.substringAfterLast(portraitUrl, ".");
    }

    public String getAvatar()
    {
        if (StringUtils.isEmpty(portraitUrl))
        {
            return "";
        }

        return StringUtils.substringBeforeLast(portraitUrl, ".") + "_avatar." + StringUtils.substringAfterLast(portraitUrl, ".");
    }

    public String getPhoto()
    {
        if (StringUtils.isEmpty(portraitUrl))
        {
            return "";
        }

        return StringUtils.substringBeforeLast(portraitUrl, ".") + "_photo." + StringUtils.substringAfterLast(portraitUrl, ".");
    }

    public String getPortraitUrl()
    {
        return portraitUrl;
    }

    /*
    public String getIcon()
    {
        if (portraitUrl == null || portraitUrl.isEmpty())
        {
            if (externalPhoto != null)
            {
                return externalPhoto;
            }
        }

        String photoUrl = getPortraitUrl();
        return StringUtils.substringBeforeLast(photoUrl, ".") + "_icon." + StringUtils.substringAfterLast(photoUrl, ".");
    }

    public String getAvatar()
    {
        if (portraitUrl == null || portraitUrl.isEmpty())
        {
            if (externalPhoto != null)
                return externalPhoto;
        }

        String photoUrl = getPortraitUrl();
        return StringUtils.substringBeforeLast(photoUrl, ".") + "_avatar." + StringUtils.substringAfterLast(photoUrl, ".");
    }

    public String getPhoto()
    {
        if (portraitUrl == null || portraitUrl.isEmpty())
        {
            if (externalPhoto != null)
                return externalPhoto;
        }

        String photoUrl = getPortraitUrl();
        return StringUtils.substringBeforeLast(photoUrl, ".") + "_photo." + StringUtils.substringAfterLast(photoUrl, ".");
    }
    */

    public void setPortraitUrl(String portraitUrl)
    {
        this.portraitUrl = portraitUrl;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getIdentity()
    {
        return identity;
    }

    public void setIdentity(String identity)
    {
        this.identity = identity;;
    }

    public int getEarnedScore()
    {
        return earnedScore;
    }

    public int getOpenProjects()
    {
        return openProjects;
    }

    public int getJoinProjects()
    {
        return joinProjects;
    }

    public int getListProjects()
    {
        return listProjects;
    }

    public int getInterviews()
    {
        return interviews;
    }

    public int getVerifications()
    {
        return verifications;
    }

    public int getAppliedTasks()
    {
        return appliedTasks;
    }

    public int getInvolvedTasks()
    {
        return involvedTasks;
    }

    public int getFinishedTasks()
    {
        return finishedTasks;
    }

    public int getFinishedRate()
    {
        if (finishedTasks == 0)
            return 0;

        return ((involvedTasks * 100) / finishedTasks);
    }

    public void setAppliedTasks(int appliedTasks)
    {
        this.appliedTasks = appliedTasks;
    }

    public void setInvolvedTasks(int involvedTasks)
    {
        this.involvedTasks = involvedTasks;
    }

    public void setFinishedTasks(int finishedTasks)
    {
        this.finishedTasks = finishedTasks;
    }

    public void setEarnedScore(int earnedScore)
    {
        this.earnedScore = earnedScore;
    }

    public void setOpenProjects(int openProjects)
    {
        this.openProjects = openProjects;
    }

    public void setJoinProjects(int joinProjects)
    {
        this.joinProjects = joinProjects;
    }

    public void setListProjects(int listProjects)
    {
        this.listProjects = listProjects;
    }

    public void setInterviews(int interviews)
    {
        this.interviews = interviews;
    }

    public void setVerifications(int verifications)
    {
        this.verifications = verifications;
    }

    public int getMessages()
    {
        return messages;
    }

    public void setMessages(int messages)
    {
        this.messages = messages;
    }

    public int getNotices()
    {
        return notices;
    }

    public void setNotices(int notices)
    {
        this.notices = notices;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public boolean getIsAdmin()
    {
        return (userType != null && userType.equalsIgnoreCase("admin"));
    }
}