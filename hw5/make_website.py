# Student Name in Canvas: QIWEN LUO
# Penn ID: 28188168
# Did you do this homework on your own (yes / no): yes
# Resources used outside course materials:
# 1. python enumerate: https://www.w3schools.com/python/python_try_except.asp


def surround_block(tag, text):
    """
    Surrounds the given text with the given html tag and returns the string.
    """

    html_text = "<" + tag + ">" + text + "</" + tag + ">"

    return html_text

def create_email_link(email_address):
    """
    Creates an email link with the given email_address.
    To cut down on spammers harvesting the email address from the webpage,
    displays the email address with [aT] instead of @.

    Example: Given the email address: lbrandon@wharton.upenn.edu
    Generates the email link: <a href="mailto:lbrandon@wharton.upenn.edu">lbrandon[aT]wharton.upenn.edu</a>

    Note: If, for some reason the email address does not contain @,
    use the email address as is and don't replace anything.
    """
    address_at_replace = email_address.replace("@", "[aT]")

    html_email = "<a href=\"mailto:" + email_address + "\">" + address_at_replace + "</a>"

    return html_email

def load_in_lines(input_file):
    """
    Return the file in lines.
    """
    f = open(input_file)
    return f.readlines()

def get_name(input_file):
    """
    Get the name from the input file.
    The name should always at the top of files.
    Return "Invalida Name" if it's invalid
    """
    # name will always at the top
    name = input_file[0].strip(" ")
    # check the first character
    if name[0].islower():
        name = "Invalid Name"
    else:
        name = name.strip("\n")
    
    return name

def get_section_indexes(input_file):
    """
    Find the line index for each sections.
    """
    for i, lines in enumerate(input_file):
        if "Courses" in lines:
            course_index = i
        elif "Projects" in lines:
            project_index_start = i + 1
        elif "----------" in lines:
            project_index_stop = i 
        elif "@" in lines:
            email_index = i

    return course_index, project_index_start, project_index_stop, email_index

def get_email(input_file, email_index):
    """
    Fine the email line, validate and return.
    If it is invalid, return an empty string.
    """
    email = input_file[email_index].strip(" \t\n")
    # validation
    # check for numbers
    email_parts = email.split("@")
    for i in email_parts:
        for j in i:
            if j.isnumeric():
                return ""
    # check for lowercase after @     
    for i in email_parts[1]:
        if i.islower() == False:
            if i != ".":
                return ""
    # check whether end with .edu or .com
    if email_parts[1][-4:] == ".edu" or email_parts[1][-4:] == ".com":
        return email
    else:
        return ""
    
def get_courses(input_file, course_index):
    """
    Get courses from input file.
    Return a course list.
    """
    courses = []
    # split into a list
    course_temp = input_file[course_index].split(",")
    # normalize the first course and the last course
    course_temp[0] = course_temp[0].replace("Courses", "")
    course_temp[-1] = course_temp[-1].strip("\n")
    # discard all non-alphabet character in the front
    # https://www.w3schools.com/python/ref_func_enumerate.asp
    # fromatting courses
    for i, course_single in enumerate(course_temp):
        for j, char in enumerate(course_single):
            # discard non alphabet char at the beginning
            if char.isalpha():
                courses.append(course_single[j:].strip(" \t"))
                break
    
    courses_str = ""
    for i in range(len(courses)):
        courses_str = courses_str + courses[i]
        if i != len(courses) - 1:
            courses_str = courses_str + ", "
    
    return courses_str

def get_project(input_file, project_index_start, project_index_end):
    project = []
    project_temp = input_file[project_index_start : project_index_end]
    for i, project_single in enumerate(project_temp):
        for j, char in enumerate(project_single):
            if char.isalpha():
                project.append(project_single[j:-1].rstrip())
                break

    return project


def generate_html(txt_input_file, html_output_file):
    """
    Loads given txt_input_file,
    gets the name, email address, list of projects, and list of courses,
    then writes the info to the given html_output_file.

    # Hint(s):
    # call function(s) to write the name, email address, list of projects, and list of courses to the given html_output_file
    """
    # load the file
    resume = load_in_lines(txt_input_file)

    # get name
    name = get_name(resume)

    # searching for indexes
    course_index, project_index_start, project_index_end, email_index = get_section_indexes(resume)

    # get email address
    email = get_email(resume, email_index)

    # get courses
    courses = get_courses(resume, course_index)
    
    # get project
    project = get_project(resume, project_index_start, project_index_end)
    
    # read the html templet into memory
    html = load_in_lines("resume_template.html")[:-2]

    # basic information section
    html.append("<div>\n")
    html.append(surround_block("h1", name) + "\n")
    html.append("<p>Email:" + create_email_link(email) + "</p>\n")
    html.append("</div>\n")

    # project section
    html.append("<div>\n")
    html.append(surround_block("h2", "Projects") + "\n")
    html.append("<ul>\n")
    for i in project:
        html.append(surround_block("li", i) + "\n")
    html.append("</ul>\n")
    html.append("</div>\n")

    # courses section
    html.append("<div>\n")
    html.append(surround_block("h3", "Courses") + "\n")
    html.append(surround_block("span", courses) + "\n")
    html.append("</div>\n")

    # ending of html file
    html.append("</div>\n") # ? ?
    html.append("</body>\n")
    html.append("</html>\n")

    # write to file
    f = open(html_output_file, "w")
    f.writelines(html)
    
def main():

    # DO NOT REMOVE OR UPDATE THIS CODE
    # generate resume.html file from provided sample resume.txt
    # generate_html('resume.txt', 'resume.html')

    # DO NOT REMOVE OR UPDATE THIS CODE.
    # Uncomment each call to the generate_html function when you’re ready
    # to test how your program handles each additional test resume.txt file
    generate_html('TestResumes/resume_bad_name_lowercase/resume.txt', 'TestResumes/resume_bad_name_lowercase/resume.html')
    generate_html('TestResumes/resume_courses_w_whitespace/resume.txt', 'TestResumes/resume_courses_w_whitespace/resume.html')
    generate_html('TestResumes/resume_courses_weird_punc/resume.txt', 'TestResumes/resume_courses_weird_punc/resume.html')
    generate_html('TestResumes/resume_projects_w_whitespace/resume.txt', 'TestResumes/resume_projects_w_whitespace/resume.html')
    generate_html('TestResumes/resume_projects_with_blanks/resume.txt', 'TestResumes/resume_projects_with_blanks/resume.html')
    generate_html('TestResumes/resume_template_email_w_whitespace/resume.txt', 'TestResumes/resume_template_email_w_whitespace/resume.html')
    generate_html('TestResumes/resume_wrong_email/resume.txt', 'TestResumes/resume_wrong_email/resume.html')

    # If you want to test additional resume files, call the generate_html function with the given .txt file
    # and desired name of output .html file

if __name__ == '__main__':
    main()