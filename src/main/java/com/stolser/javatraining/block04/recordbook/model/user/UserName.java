package com.stolser.javatraining.block04.recordbook.model.user;

import static com.google.common.base.Preconditions.*;

/**
 * Represents an abstraction of user name.
 */
public class UserName implements Cloneable {
    private String firstName;
    private String lastName;
    private String extraName;
    private String nickname;

    public UserName(String firstName, String lastName) {
        this(firstName, lastName, null, null);
    }

    public UserName(String firstName, String lastName, String extraName, String nickname) {
        checkNotNull(firstName);
        checkNotNull(lastName);

        this.firstName = firstName;
        this.lastName = lastName;
        this.extraName = extraName;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return String.format("UserName{firstName = '%s', lastName = '%s', extraName = '%s'}",
                firstName, lastName, extraName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName)) return false;

        UserName userName = (UserName) o;

        if (!firstName.equals(userName.firstName)) return false;
        if (!lastName.equals(userName.lastName)) return false;
        return extraName != null ? extraName.equals(userName.extraName) : userName.extraName == null;

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (extraName != null ? extraName.hashCode() : 0);
        return result;
    }

    public String getShortForm() {
        return String.format("%s %c.", lastName, firstName.charAt(0));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        checkNotNull(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        checkNotNull(lastName);
        this.lastName = lastName;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        checkNotNull(extraName);
        this.extraName = extraName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullForm() {
        StringBuilder builder = new StringBuilder(String.format("%s %s", lastName, firstName));

        if ((extraName != null) && (! "".equals(extraName))) {
            builder.append(String.format(" %s", extraName));
        }

        if ((nickname != null) && (! "".equals(nickname))) {
            builder.append(String.format(" (known as '%s')", nickname));
        }

        return builder.toString();
    }

    @Override
    public UserName clone() {
        UserName clone;

        try {
            clone = (UserName) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        return clone;
    }
}
