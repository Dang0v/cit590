def surround_block(tag, text):
    """
    Surrounds the given text with the given html tag and returns the string.
    """

    html = "<" + tag + ">" + text + "</" + tag + ">"

    return html

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

    # insert code
    pass

def generate_html(txt_input_file, html_output_file):
    """
    Loads given txt_input_file,
    gets the name, email address, list of projects, and list of courses,
    then writes the info to the given html_output_file.

    # Hint(s):
    # call function(s) to load given txt_input_file
    # call function(s) to get name
    # call function(s) to get email address
    # call function(s) to get list of projects
    # call function(s) to get list of courses
    # call function(s) to write the name, email address, list of projects, and list of courses to the given html_output_file
    """

    f = open(txt_input_file, "r")
    resume = f.readlines()
    # print(resume)

    # get name
    # name will always be written at the top
    # print(resume[0])
    name = resume[0]
    # check the first character
    if name[0].islower():
        name = "Invalid Name"
    else:
        name = name[:-2]
    # print(name)

    # searching for indexes
    for i, lines in enumerate(resume):
        if "Courses" in lines:
            course_index = i
        elif "Projects" in lines:
            project_index_start = i + 1
        elif "----------" in lines:
            project_index_stop = i 
        elif "@" in lines:
            email_index = i

    # print(course_index)
    # print(project_index_start)
    # print(project_index_stop)
    # print(email_index)

    # get email address
    email = resume[email_index].strip(" ")
    email = email[:-1]
    # TODO: email validation
    # print(email)

    # get courses
    # fromatting courses
    # normalize the first course
    course = resume[course_index].split(",")
    course[0] = course[0].replace("Courses", "")
    # discard all non-alphabet character in the front
    # https://www.w3schools.com/python/ref_func_enumerate.asp
    for i, course_single in enumerate(course):
        for j, char in enumerate(course_single):
            if char.isalpha():
                # the last course will have "\n" at the end
                if course_single == course[-1]:
                    course[i] = course_single[j: -2]
                    break
                course[i] = course_single[j:]
                break
    # print(course)

    # get project
    project = resume[project_index_start : project_index_stop]
    for i, project_single in enumerate(project):
        for j, char in enumerate(project):
            if char.isalpha():
                project[i] = project_single[j:-2]

    print(project)
    

    
    


    


def main():

    # DO NOT REMOVE OR UPDATE THIS CODE
    # generate resume.html file from provided sample resume.txt
    generate_html('resume.txt', 'resume.html')

    # DO NOT REMOVE OR UPDATE THIS CODE.
    # Uncomment each call to the generate_html function when you’re ready
    # to test how your program handles each additional test resume.txt file
    #generate_html('TestResumes/resume_bad_name_lowercase/resume.txt', 'TestResumes/resume_bad_name_lowercase/resume.html')
    #generate_html('TestResumes/resume_courses_w_whitespace/resume.txt', 'TestResumes/resume_courses_w_whitespace/resume.html')
    #generate_html('TestResumes/resume_courses_weird_punc/resume.txt', 'TestResumes/resume_courses_weird_punc/resume.html')
    #generate_html('TestResumes/resume_projects_w_whitespace/resume.txt', 'TestResumes/resume_projects_w_whitespace/resume.html')
    #generate_html('TestResumes/resume_projects_with_blanks/resume.txt', 'TestResumes/resume_projects_with_blanks/resume.html')
    #generate_html('TestResumes/resume_template_email_w_whitespace/resume.txt', 'TestResumes/resume_template_email_w_whitespace/resume.html')
    #generate_html('TestResumes/resume_wrong_email/resume.txt', 'TestResumes/resume_wrong_email/resume.html')

    # If you want to test additional resume files, call the generate_html function with the given .txt file
    # and desired name of output .html file

if __name__ == '__main__':
    main()